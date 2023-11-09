package com.background.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.background.manager.constant.RegexConstant;
import com.background.manager.constants.UserStatusConstant;
import com.background.manager.constants.ZsCSConstants;
import com.background.manager.convert.TSysUserConvertor;
import com.background.manager.dto.request.console.getCustomerListRequest;
import com.background.manager.dto.request.user.*;
import com.background.manager.dto.response.user.TSysUserDigestDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.mapper.TSysUserMapper;
import com.background.manager.model.ResourceApplicationForm;
import com.background.manager.model.TSysUser;
import com.background.manager.service.ResourceApplicationFormService;
import com.background.manager.service.TSysUserService;
import com.background.manager.util.IotHttpClient;
import com.background.manager.util.Sm2Util;
import com.background.manager.util.SmsUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TSysUserServiceImpl extends ServiceImpl<TSysUserMapper, TSysUser> implements TSysUserService {

    @Resource
    private TSysUserConvertor tSysUserConvertor;

    @Resource
    private ResourceApplicationFormService resourceApplicationFormService;

    @Autowired
    private RedisTemplate redisTemplate;

    private final static String ZS_SERVICE_URL = "https://uconsole.nbaicc.com";

    private final static String ZS_MANAGER_SERVICE_URL = "https://mgt.nbaicc.com";

    private final static String managerUserId = "25000";

    private final static Integer NOT_EXIST_RESOURCE_APPLICANT_FORM=0;

    private final static Integer EXIST_RESOURCE_APPLICANT_FORM=1;


    @Override
    public IPage<TSysUserDigestDTO> pageQuery(PageQueryTSysUserRequest request) {
        Page<TSysUser> page = this.lambdaQuery()
                .eq(ObjectUtil.isNotNull(request.getApplyStatus()),TSysUser::getApplyStatus,request.getApplyStatus())
                .and(StringUtils.isNotBlank(request.getKeyWords()),keyword->
                        keyword.like(TSysUser::getUsername,request.getKeyWords())
                        .or().like(TSysUser::getCompanyName,request.getKeyWords())
                         .or().like(TSysUser::getContactName,request.getKeyWords())
                         .or().like(TSysUser::getContactPhone,request.getKeyWords()))
            /**一级排序：申请状态，二级排序：注册时间*/
           .orderByAsc(TSysUser::getApplyStatus)
           .orderByDesc(TSysUser::getRegisterTime)
           .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtils.isEmpty(page.getRecords())){
            return new Page<>();
        }
        List<TSysUserDigestDTO> tSysUserDigestDTOS=new ArrayList<>();
        for (TSysUser tSysUser:page.getRecords()){
            TSysUserDigestDTO tSysUserDigestDTO = tSysUserConvertor.toTSysUserDigestDTO(tSysUser);
            //封装用户资源申请表状态
            ResourceApplicationForm resourceApplicationForm = resourceApplicationFormService.getOne(
                    new LambdaQueryWrapper<ResourceApplicationForm>().eq(ResourceApplicationForm::getUserId,tSysUser.getUserId()));
            if (ObjectUtil.isNotNull(resourceApplicationForm)){
                tSysUserDigestDTO.setExistResourceApplicantForm(EXIST_RESOURCE_APPLICANT_FORM);
            }else {
                tSysUserDigestDTO.setExistResourceApplicantForm(NOT_EXIST_RESOURCE_APPLICANT_FORM);
            }
            tSysUserDigestDTOS.add(tSysUserDigestDTO);
        }
        Page<TSysUserDigestDTO> pages=new Page<>();
        pages.setCurrent(page.getCurrent());
        pages.setPages(page.getPages());
        pages.setSize(page.getSize());
        pages.setTotal(page.getTotal());
        pages.setRecords(tSysUserDigestDTOS);
        return pages;
    }

    @Override
    public boolean delete( DeleteUserRequest request) {
        TSysUser tSysUser = this.getOne(new LambdaQueryWrapper<TSysUser>()
                .eq(TSysUser::getUserId, request.getUserId()));
        if (ObjectUtil.isNull(tSysUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return this.removeById(tSysUser);
    }

    @Override
    @Transactional
    public void ActivateAccount(ActivateAccountRequest request) {
        TSysUser tSysUser = this.getOne(new LambdaQueryWrapper<TSysUser>()
                .eq(TSysUser::getUserId, request.getUserId()));
        if (ObjectUtil.isNull(tSysUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        tSysUser.setApplyStatus(UserStatusConstant.APPROVED);
        updateById(tSysUser);
        //更新算力资源申请表
        ResourceApplicationForm resourceApplicationForm = resourceApplicationFormService.getOne(
                new LambdaQueryWrapper<ResourceApplicationForm>().eq(ResourceApplicationForm::getUserId,tSysUser.getUserId()));
        if (ObjectUtil.isNotNull(resourceApplicationForm)){
            if (StringUtils.isNotBlank(request.getConclusion())){
                resourceApplicationForm.setConclusion(request.getConclusion());
                resourceApplicationFormService.updateById(resourceApplicationForm);
            }
        }
        //在云星平台通过用户审核
        this.zsActivateAccount(tSysUser.getZsUserId());
        /**发送审核通过短信*/
        //校验手机号格式
        if(!tSysUser.getContactPhone().matches(RegexConstant.Regex.PHONE_REGEX)){
            throw new BadRequestException(ResultCodeEnum.PARAM_INVALID);
        }
        if(!Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent("SMS_DUPLICATE_"+tSysUser.getContactPhone(),true,1, TimeUnit.MINUTES))){
            throw new BadRequestException(ResultCodeEnum.SMS_TOO_FREQUENT);
        }
        //发送短信验证码
        SmsUtil.sendSms(tSysUser.getContactPhone());
    }

    @Override
    @Transactional
    public void rejectAccount(RejectAccountRequest request) {
        TSysUser tSysUser = this.getOne(new LambdaQueryWrapper<TSysUser>()
                .eq(TSysUser::getUserId, request.getUserId()));
        if (ObjectUtil.isNull(tSysUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        tSysUser.setApplyStatus(UserStatusConstant.Reject);
        updateById(tSysUser);
        //更新算力资源申请表
        ResourceApplicationForm resourceApplicationForm = resourceApplicationFormService.getOne(
                new LambdaQueryWrapper<ResourceApplicationForm>().eq(ResourceApplicationForm::getUserId,tSysUser.getUserId()));
        if (ObjectUtil.isNotNull(resourceApplicationForm)){
            if (StringUtils.isNotBlank(request.getConclusion())){
                resourceApplicationForm.setConclusion(request.getConclusion());
                resourceApplicationFormService.updateById(resourceApplicationForm);
            }
        }
        this.zsRejectAccount(tSysUser.getZsUserId(),request.getRemark());
    }

    @Override
    public List<TSysUserDigestDTO> listReceptionUser(ListReceptionUserRequest request) {
        List<TSysUser> list = this.list(new LambdaQueryWrapper<TSysUser>()
                .like(StringUtils.isNotBlank(request.getUsername()), TSysUser::getUsername, request.getUsername()));
        if (CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return tSysUserConvertor.toTSysUserDigestDTOS(list);
    }

    @Override
    public TSysUser getUserByUserId(String userId) {
        return this.getOne(new LambdaQueryWrapper<TSysUser>()
                .eq(TSysUser::getUserId,userId));
    }

    @Override
    public List<TSysUser> getUserByQueryRequest(getCustomerListRequest request) {
        return this.list(new LambdaQueryWrapper<TSysUser>()
                .like(StringUtils.isNotBlank(request.getCompanyName()),TSysUser::getCompanyName,request.getCompanyName())
                .like(StringUtils.isNotBlank(request.getContactName()),TSysUser::getContactName,request.getContactName())
                .like(StringUtils.isNotBlank(request.getUsername()),TSysUser::getUsername,request.getUsername())
                .eq(StringUtils.isNotBlank(request.getContactPhone()),TSysUser::getContactPhone,request.getContactPhone())
                .eq(TSysUser::getApplyStatus,UserStatusConstant.APPROVED.intValue())
                .orderByDesc(TSysUser::getRegisterTime));
    }

    public JSONObject zsGetToken(String userId, String type){
        Object cacheResult = redisTemplate.opsForValue().get("token"+type+userId);
        if(cacheResult != null && cacheResult instanceof JSONObject){
            return (JSONObject) cacheResult;
        }
        JSONObject queryPlain = new JSONObject();
        queryPlain.put("userId",userId);
        queryPlain.put("moduleType",type);
        queryPlain.put("timestamp",System.currentTimeMillis()/1000);
        String encryptedData;
        try {
            queryPlain.put("sign", Sm2Util.sign(ZsCSConstants.zsServerPrivate, queryPlain));
            encryptedData = Sm2Util.encrypt(ZsCSConstants.zsPublic, queryPlain.toJSONString());
        }catch (Exception e){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.SM2_ENCRYPTION_FAIL);
        }
        IotHttpClient httpClient = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_JSON)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_POST)
                .url(ZS_SERVICE_URL+"/api/v1/customize/users/getManagerToken")
                .addBodyParam("userInfo",encryptedData).build();
        String resultStr = httpClient.execute();
        JSONObject result = JSON.parseObject(resultStr);
        if(result != null && Integer.valueOf(200).equals(result.getInteger("code"))){
            JSONObject retJson = new JSONObject();
            retJson.put("subject",result.get("subject"));
            retJson.put("session_id",result.get("session_id"));
            retJson.put("token",result.get("token"));
            retJson.put("xsrf",httpClient.getXsrf());
            redisTemplate.opsForValue().set("token"+type+userId,retJson,110, TimeUnit.SECONDS);
            return retJson;
        }else if(result != null){
            throw new BadRequestException(ResultCodeEnum.AUDIT_FAIL,result.getString("message"));
        }else{
            throw new BadRequestException(ResultCodeEnum.AUDIT_FAIL,"获取智算token失败");
        }
    }

    public boolean zsActivateAccount(String userId){
        JSONObject tokenJson = zsGetToken(managerUserId,"bss");
        String resultStr = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_JSON)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_PUT)
                .url(ZS_MANAGER_SERVICE_URL+"/api/v1/oss/users/"+userId+"/activation")
                .addHeaderParam("authorization",tokenJson.getString("token"))
                .addHeaderParam("moduleType","bss")
                .addHeaderParam("subject",tokenJson.getString("subject"))
                .addHeaderParam("sessionId",tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN",tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie","XSRF-TOKEN="+tokenJson.getString("xsrf"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))){
           return true;
        }
        String errorMessage="智算中心通过用户审核失败";
        throw new BadRequestException(BackgroundManagementResultCodeEnum.APPROVE_ACCOUNT_FAIL,errorMessage);
    }

    public boolean zsRejectAccount(String userId, String remark){
        JSONObject tokenJson = zsGetToken(managerUserId,"bss");
        String resultStr = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_JSON)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_PUT)
                .url(ZS_MANAGER_SERVICE_URL+"/api/v1/oss/users/refuse")
                .addHeaderParam("authorization",tokenJson.getString("token"))
                .addHeaderParam("moduleType","bss")
                .addHeaderParam("subject",tokenJson.getString("subject"))
                .addHeaderParam("sessionId",tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN",tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie","XSRF-TOKEN="+tokenJson.getString("xsrf"))
                .addBodyParam("userId",userId)
                .addBodyParam("remark",remark)
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))){
            return true;
        }else {
            String errorMessage="智算中心拒绝用户审核失败";
            throw new BadRequestException(BackgroundManagementResultCodeEnum.REJECT_ACCOUNT_FAIL,errorMessage);
        }
    }

}


