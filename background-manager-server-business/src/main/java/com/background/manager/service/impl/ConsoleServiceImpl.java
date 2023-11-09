package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.background.manager.constants.ZsCSConstants;
import com.background.manager.convert.TSysUserConvertor;
import com.background.manager.dto.request.console.*;
import com.background.manager.dto.request.zs.*;
import com.background.manager.dto.response.console.CustomerListDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.model.TSysUser;
import com.background.manager.service.ConsoleService;
import com.background.manager.service.TSysUserService;
import com.background.manager.util.IotHttpClient;
import com.background.manager.util.Sm2Util;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${sm2.server.zs.privateKey}")
    private String zsServerPrivate;
    @Value("${sm2.zs.publicKey}")
    private String zsPublic;
    @Value("${zs.console.url}")
    private String zsConsoleUrl;
    @Value("${zs.manage.url}")
    private String zsManageUrl;
    @Value("${cs.console.url}")
    private String csConsoleUrl;
//    @Value("${zs.manage.userId}")
//    private String managerUserId;

    private final static String ZS_SERVICE_URL = "https://uconsole.nbaicc.com";

    private final static String ZS_MANAGER_SERVICE_URL = "https://mgt.nbaicc.com";

    private final static String managerUserId = "25000";
    @Autowired
    private TSysUserService tSysUserService;
    @Autowired
    private TSysUserConvertor convertor;

    public String csGetToken() {
        Object cacheResult = redisTemplate.opsForValue().get("token_cs0");
        if (cacheResult != null && cacheResult instanceof String) {
            return (String) cacheResult;
        }
        String resultStr = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_FORM_URLENCODED)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_POST)
                .url(csConsoleUrl + "/ac/openapi/v2/tokens")
                .addHeaderParam("user", "admin")
                .addHeaderParam("password", "admin@Gridview@2023")
                .addHeaderParam("orgId", "5784c8548a194e2e01d96f3ad559a3d9")
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            JSONArray data = result.getJSONArray("data");
            for (int i = 0; i < data.size(); i++) {
                JSONObject item = data.getJSONObject(i);
                redisTemplate.opsForValue().set("token_cs" + item.getString("clusterId"), item.getString("token"), 700, TimeUnit.MINUTES);
            }
            return (String) redisTemplate.opsForValue().get("token_cs0");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "获取超算token失败");
        }
    }

    @Override
    public JSONObject csCommonUserList(csCommonUserListRequest request) throws UnsupportedEncodingException {

        String resultStr = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_JSON)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_POST)
                .url(csConsoleUrl + "/ac/openapi/v2/commonUserList")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("page", Integer.valueOf(request.getPage()).toString())
                .addBodyParam("size", Integer.valueOf(request.getSize()).toString())
                .addBodyParam(StringUtils.isNotBlank(request.getRoleId()), "roleId", request.getRoleId())
                .addBodyParam(CollectionUtil.isNotEmpty(request.getUserName()), "userNames", String.join(",", request.getUserName()))
                .addBodyParam(CollectionUtil.isNotEmpty(request.getFullNames()), "fullNames", String.join(",", request.getFullNames()))
                .addBodyParam(StringUtils.isNotBlank(request.getUserType()), "userType", request.getUserType())
                .addBodyParam(StringUtils.isNotBlank(request.getMobilePhoneNum()), "mobilePhoneNum", request.getMobilePhoneNum())
                .addBodyParam(StringUtils.isNotBlank(request.getEmail()), "email", request.getEmail())
                .addBodyParam(StringUtils.isNotBlank(request.getLastLoginTimeStart()), "lastLoginTimeStart", URLEncoder.encode(request.getLastLoginTimeStart(), "UTF-8"))
                .addBodyParam(StringUtils.isNotBlank(request.getLastLoginTimeEnd()), "lastLoginTimeEnd", URLEncoder.encode(request.getLastLoginTimeEnd(), "UTF-8"))
                .addBodyParam(StringUtils.isNotBlank(request.getAccountName()), "accountName", request.getAccountName())
                .addBodyParam(StringUtils.isNotBlank(request.getAccountType()), "accountType", request.getAccountType())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject queryResourceDisplayList(queryResourceDisplayListRequest request) {
        String resultStr = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_FORM_URLENCODED)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_POST)
                .url(csConsoleUrl + "/ac/openapi/v2/resource/queryResourceDisplayList")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("start", request.getStart())
                .addBodyParam("limit", request.getLimit())
                .addBodyParam("centerId", request.getCenterId())
                .addBodyParam(StringUtils.isNotBlank(request.getLabel()), "label", request.getLabel())
                .addBodyParam(StringUtils.isNotBlank(request.getResourceId()), "resourceId", request.getResourceId())
                .addBodyParam(StringUtils.isNotBlank(request.getSpecifications()), "specifications", request.getSpecifications())
                .addBodyParam(StringUtils.isNotBlank(request.getStatus()), "status", request.getStatus())
                .addBodyParam(StringUtils.isNotBlank(request.getType()), "type", request.getType())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONArray queryActiveResources() {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/resource/queryActiveResources")
                .addHeaderParam("token", csGetToken())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONArray("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public Boolean updateResourceDisplayStatus(updateResourceDisplayStatusRequest request) {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_POST)
                .mediaType(IotHttpClient.MediaType.APPLICATION_FORM_URLENCODED)
                .url(csConsoleUrl + "/ac/openapi/v2/resource/updateResourceDisplayStatus")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("id", request.getId())
                .addBodyParam("status", "OFF")
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getBoolean("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public Boolean updateResourceDisplay(updateResourceDisplayRequest request) {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_POST)
                .mediaType(IotHttpClient.MediaType.APPLICATION_FORM_URLENCODED)
                .url(csConsoleUrl + "/ac/openapi/v2/resource/updateResourceDisplay")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("id", request.getId())
                .addBodyParam("status", "ON")
                .addBodyParam(StringUtils.isNotBlank(request.getLabel()), "label", request.getLabel())
                .addBodyParam("formalClusterInfo", request.getFormalClusterInfo())
                .addBodyParam(StringUtils.isNotBlank(request.getTrialClusterInfo()), "trialClusterInfo", request.getTrialClusterInfo())
                .addBodyParam(StringUtils.isNotBlank(request.getDelFormalClusters()), "delFormalClusters", request.getDelFormalClusters())
                .addBodyParam(StringUtils.isNotBlank(request.getDelTrialClusters()), "delTrialClusters", request.getDelTrialClusters())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getBoolean("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject getItem(getItemRequest request) {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/resource/item")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("start", request.getStart().toString())
                .addBodyParam("limit", request.getLimit().toString())
                .addBodyParam(StringUtils.isNotBlank(request.getStatus()), "status", request.getStatus())
                .addBodyParam(StringUtils.isNotBlank(request.getType()), "type", request.getType())
                .addBodyParam(StringUtils.isNotBlank(request.getService()), "service", request.getService())
                .addBodyParam(StringUtils.isNotBlank(request.getName()), "name", request.getName())
                .addBodyParam(StringUtils.isNotBlank(request.getResourceId()), "resourceId", request.getResourceId())
                .addBodyParam(StringUtils.isNotBlank(request.getSpecifications()), "specifications", request.getSpecifications())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONArray getChargeRules(getChargeRulesRequest request) {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/resource/chargerules")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("start", request.getStart())
                .addBodyParam("limit", request.getLimit())
                .addBodyParam("areaId", request.getAreaId())
                .addBodyParam(StringUtils.isNotBlank(request.getType()), "type", request.getType())
                .addBodyParam(StringUtils.isNotBlank(request.getStatus()), "status", request.getStatus())
                .addBodyParam(StringUtils.isNotBlank(request.getResourceId()), "resourceId", request.getResourceId())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONArray("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONArray getProduct(getProductRequest request) throws UnsupportedEncodingException {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/account/report/product")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("startTime", URLEncoder.encode(request.getStartTime(), "UTF-8"))
                .addBodyParam("endTime", URLEncoder.encode(request.getEndTime(), "UTF-8"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONArray("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONArray getCustomer(getCustomerRequest request) throws UnsupportedEncodingException {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/account/report/customer")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("startTime", URLEncoder.encode(request.getStartTime(), "UTF-8"))
                .addBodyParam("endTime", URLEncoder.encode(request.getEndTime(), "UTF-8"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            JSONArray data = result.getJSONArray("data");
            //遍历data，根据返回的accountId查询对应的用户名
            data.stream().forEach(customer -> {
                JSONObject object = (JSONObject) customer;
                String accountId = object.getString("accountId");
                String name = this.getAcUserNameByAccountId(accountId);
                object.put("name", name);
            });
            return data;
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject csOrderList(CsOrderListRequest request) throws UnsupportedEncodingException {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/account/order/list")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("accountName", StringUtils.isNotBlank(request.getAccountName()) ? request.getAccountName() : "")
                .addBodyParam(StringUtils.isNotBlank(request.getRechargeType()), "rechargeType", request.getRechargeType())
                .addBodyParam(StringUtils.isNotBlank(request.getOrderId()), "orderId", request.getOrderId())
                .addBodyParam("start", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("limit", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam("startTime", URLEncoder.encode(request.getStartTime(), "UTF-8"))
                .addBodyParam("endTime", URLEncoder.encode(request.getEndTime(), "UTF-8"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject csBillList(CsBillListRequest request) throws UnsupportedEncodingException {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/account/bill/list")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("clusterId", StringUtils.isNotBlank(request.getClusterId()) ? request.getClusterId() : "")
                .addBodyParam("accountName", StringUtils.isNotBlank(request.getAccountName()) ? request.getAccountName() : "")
                .addBodyParam("start", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("limit", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam("startTime", URLEncoder.encode(request.getStartTime(), "UTF-8"))
                .addBodyParam("endTime", URLEncoder.encode(request.getEndTime(), "UTF-8"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject csTradeList(CsTradeListRequest request) throws UnsupportedEncodingException {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/account/trade/list")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("userName", StringUtils.isNotBlank(request.getUserName()) ? request.getUserName() : "")
                .addBodyParam("tradeType", request.getTradeType())
                .addBodyParam("start", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("limit", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam("startTime", URLEncoder.encode(request.getStartTime(), "UTF-8"))
                .addBodyParam("endTime", URLEncoder.encode(request.getEndTime(), "UTF-8"))
                .addBodyParam("orderBy", "time")
                .addBodyParam("order", "desc")
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    private String getAcUserNameByAccountId(String accountId) {
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/account/bill/detail")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("accountId", accountId)
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            JSONObject data = result.getJSONObject("data");
            String name = data.getString("name");
            return StringUtils.isNotBlank(name) ? name : null;
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }


    @Override
    public IPage<CustomerListDTO> getCustomerList(getCustomerListRequest request) {
        List<TSysUser> userList = tSysUserService.getUserByQueryRequest(request);
        if (CollectionUtil.isEmpty(userList)) {
            return new Page<>();
        }
        List<CustomerListDTO> customerListDTOList = new ArrayList<>();
        for (TSysUser tSysUser : userList) {
            CustomerListDTO customerListDTO = convertor.toCustomerListDTO(tSysUser);
            //调用第三方接口获取当前用户的账单详情()
            if (StringUtils.isNotBlank(tSysUser.getCsAccountId())) {
                JSONObject result = this.listBillDetails(tSysUser.getUserId(), tSysUser.getCsAccountId());
                if (ObjectUtil.isNotNull(result)) {
                    String status = result.getString("status");
                    String balance = result.getString("balance");
                    customerListDTO.setStatus(status);
                    customerListDTO.setBalance(balance);
                }
            }
            customerListDTOList.add(customerListDTO);
        }
        IPage<CustomerListDTO> pages = new Page<>(request.getPage(), request.getSize());
        pages.setTotal(customerListDTOList.size())
                .setRecords(customerListDTOList);
        return pages;
    }

    @Override
    public JSONObject getCustomerTradeList(getCustomerTradeListRequest request) throws UnsupportedEncodingException {
        TSysUser sysUser = csAccountBaseInfo(request.getUserId());
        if (org.springframework.util.StringUtils.isEmpty(sysUser.getCsAccountId())) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CS_USER_ID_FAIL);
        }
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/account/trade/list")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("userName", sysUser.getUsername().toLowerCase())
                .addBodyParam("tradeType", request.getTradeType())
                .addBodyParam("start", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("limit", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam("startTime", URLEncoder.encode(request.getStartTime(), "UTF-8"))
                .addBodyParam("endTime", URLEncoder.encode(request.getEndTime(), "UTF-8"))
                .addBodyParam("orderBy", "time")
                .addBodyParam("order", "desc")
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONArray csResourceList(String userId) {
        TSysUser sysUser = csAccountBaseInfo(userId);
        if (StringUtils.isBlank(sysUser.getCsAccountId())) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CS_USER_ID_FAIL);
        }
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/resource/all")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("accountId", sysUser.getCsAccountId())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONArray("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    private JSONObject listBillDetails(String userId, String csAccountId) {
        TSysUser sysUser = csAccountBaseInfo(userId);
        if (org.springframework.util.StringUtils.isEmpty(sysUser.getCsAccountId())) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CS_USER_ID_FAIL);
        }
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/account/bill/detail")
                .addHeaderParam("token", csGetToken())
                .addBodyParam("accountId", sysUser.getCsAccountId())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    public TSysUser csAccountBaseInfo(String userId) {
        TSysUser sysUser = tSysUserService.getById(userId);
        if (sysUser == null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        if (!org.springframework.util.StringUtils.isEmpty(sysUser.getCsAccountId())) {
            return sysUser;
        }
        String resultStr = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_FORM_URLENCODED)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(csConsoleUrl + "/ac/openapi/v2/accountBaseInfo")
                .addHeaderParam("token", csGetToken())
                .addHeaderParam("userName", sysUser.getUsername())
//                .addHeaderParam("userName",username)
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && "0".equals(result.getString("code"))) {
            JSONObject data = result.getJSONObject("data");
            sysUser.setCsAccountId(data.getString("accountId"));
            sysUser.setCsGroupId(data.getString("groupId"));
            sysUser.setCsClusterId(data.getString("clusterId"));
            tSysUserService.updateById(sysUser);
            return sysUser;
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.HPC_PLATFORM_ERROR, result.getString("msg"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject zsProductsList(ZsProductsSearchDTO request) {
        Long categoryId = request.getCategoryId();
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/products")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam(StringUtils.isNotBlank(request.getProductNameLike()), "productNameLike", request.getProductNameLike())
                .addBodyParam(StringUtils.isNotBlank(request.getSortdatafield()), "sortdatafield", request.getSortdatafield())
                .addBodyParam(StringUtils.isNotBlank(request.getSortorder()), "sortorder", request.getSortorder())
                .addBodyParam(StringUtils.isNotBlank(request.getServiceComponent()), "serviceComponent", request.getServiceComponent())
                .addBodyParam(categoryId != null && categoryId.longValue() != 0, "categoryId", String.valueOf(categoryId))
                .addBodyParam(StringUtils.isNotBlank(request.getStatus()), "status", request.getStatus())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public Boolean zsUpdateProductStatus(zsUpdateProductStatusRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_XML)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_PUT)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/products/status/" + request.getId() + "?status=" + request.getStatus())
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getBoolean("status");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject resourceStrategy(resourceStrategyRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/products/resources")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam("mgtConsole", "true")
                .addBodyParam(StringUtils.isNotBlank(request.getProductNameLike()), "productNameLike", request.getProductNameLike())
                .addBodyParam(StringUtils.isNotBlank(request.getStatus()), "status", request.getStatus())
                .addBodyParam(StringUtils.isNotBlank(request.getOrderSn()), "orderSn", request.getOrderSn())
                .addBodyParam(StringUtils.isNotBlank(request.getProductType()), "productType", request.getProductType())
                .addBodyParam(StringUtils.isNotBlank(request.getDistributorName()), "distributorName", request.getDistributorName())
                .addBodyParam(StringUtils.isNotBlank(request.getAccountNameLike()), "accountNameLike", request.getAccountNameLike())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject billingStrategyList(billingStrategyRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/billing/strategy")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam(ObjectUtil.isNotNull(request.getStatus()), "status", String.valueOf(request.getStatus()))
                .addBodyParam(StringUtils.isNotBlank(request.getNameLike()), "nameLike", request.getNameLike())
                .addBodyParam(StringUtils.isNotBlank(request.getCategory()), "category", request.getCategory())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONArray billingStrategySpecGroup(Long strategyId) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/billing/spec_group")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("strategyId", String.valueOf(strategyId))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONArray("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public Boolean updateBillingStrategy(updateBillingStrategyRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_XML)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_PUT)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/billing/strategy/" + request.getId() + "/" + request.getStatus())
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getBoolean("status");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject productResourcesList(productResourcesListRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/products/resources")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam(StringUtils.isNotBlank(request.getProductType()),"productType", request.getProductType())
                .addBodyParam(ObjectUtil.isNotNull(request.getParentOrgSid()),"parentOrgSid", String.valueOf(request.getParentOrgSid()))
                .addBodyParam(StringUtils.isNotBlank(request.getCreateDt()),"createDt", request.getCreateDt())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject accountDeals(accountDealsRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/account_deals")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam("accountSid", request.getAccountSid())
                .addBodyParam(StringUtils.isNotBlank(request.getStartTime()), "startTime", request.getStartTime())
                .addBodyParam(StringUtils.isNotBlank(request.getEndTime()), "endTime", request.getEndTime())
                .addBodyParam(StringUtils.isNotBlank(request.getAccountNameLike()), "accountNameLike", request.getAccountNameLike())
                .addBodyParam(StringUtils.isNotBlank(request.getFlowNoLike()), "flowNonLike", request.getFlowNoLike())
                .addBodyParam(StringUtils.isNotBlank(request.getType()), "type", request.getType())
                .addBodyParam(StringUtils.isNotBlank(request.getTradeType()), "tradeType", request.getTradeType())
                .addBodyParam(StringUtils.isNotBlank(request.getTradeChannel()), "tradeChannel", request.getTradeChannel())
                .addBodyParam(StringUtils.isNotBlank(request.getTradeNoLike()), "tradeNoLike", request.getTradeNoLike())
                .addBodyParam(StringUtils.isNotBlank(request.getOrderNoLike()), "orderNoLike", request.getOrderNoLike())
                .addBodyParam(StringUtils.isNotBlank(request.getSortdatafield()), "sortdatafield", request.getSortdatafield())
                .addBodyParam(StringUtils.isNotBlank(request.getSortorder()), "sortorder", request.getSortorder())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject getOperationStatistics(orderStatisticsRequest request) throws UnsupportedEncodingException {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/operation/statistics/order_type")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("startTime", URLEncoder.encode(request.getStartTime(), "UTF-8"))
                .addBodyParam("endTime", URLEncoder.encode(request.getEndTime(), "UTF-8"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }


    @Override
    public JSONObject getAccountStatistics(accountStatisticsRequest request) throws UnsupportedEncodingException {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/operation/statistics/order_account")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam("startTime", URLEncoder.encode(request.getStartTime(), "UTF-8"))
                .addBodyParam("endTime", URLEncoder.encode(request.getEndTime(), "UTF-8"))
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject accountDealsList(accountDealsListRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/account_deals")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam(StringUtils.isNotBlank(request.getStartTime()), "startTime", request.getStartTime())
                .addBodyParam(StringUtils.isNotBlank(request.getEndTime()), "endTime", request.getEndTime())
                .addBodyParam(StringUtils.isNotBlank(request.getAccountNameLike()), "accountNameLike", request.getAccountNameLike())
                .addBodyParam(StringUtils.isNotBlank(request.getFlowNoLike()), "flowNonLike", request.getFlowNoLike())
                .addBodyParam(StringUtils.isNotBlank(request.getType()), "type", request.getType())
                .addBodyParam(StringUtils.isNotBlank(request.getTradeType()), "tradeType", request.getTradeType())
                .addBodyParam(StringUtils.isNotBlank(request.getTradeChannel()), "tradeChannel", request.getTradeChannel())
                .addBodyParam(StringUtils.isNotBlank(request.getTradeNoLike()), "tradeNoLike", request.getTradeNoLike())
                .addBodyParam(StringUtils.isNotBlank(request.getOrderNoLike()), "orderNoLike", request.getOrderNoLike())
                .addBodyParam(StringUtils.isNotBlank(request.getBillNoLike()), "billNoLike", request.getBillNoLike())
                .addBodyParam(StringUtils.isNotBlank(request.getSortdatafield()), "sortdatafield", request.getSortdatafield())
                .addBodyParam(StringUtils.isNotBlank(request.getSortorder()), "sortorder", request.getSortorder())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject getAvailableResource(getAvailableResourceRequest request) {
        //获取用户对象
        TSysUser user = tSysUserService.getById(request.getUserId());
        if (user == null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        JSONObject tokenJson = zsGetToken(user.getZsUserId(), "console");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_SERVICE_URL + "/api/v1/bss/products/resources")
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "console")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("productType", "DRP")
                .addBodyParam("parentOrgSid", user.getZsOrgSid())
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject orderList(orderListRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/oss/audit/approval/orders")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam(StringUtils.isNotBlank(request.getOrderSnLike()), "orderSnLike", request.getOrderSnLike())
                .addBodyParam(StringUtils.isNotBlank(request.getNameLike()), "nameLike", request.getNameLike())
                .addBodyParam(StringUtils.isNotBlank(request.getOrgNameLke()), "orgNameLke", request.getOrgNameLke())
                .addBodyParam(StringUtils.isNotBlank(request.getProductNameLike()), "productNameLike", request.getProductNameLike())
                .addBodyParam(StringUtils.isNotBlank(request.getTypeName()), "typeName", request.getTypeName())
                .addBodyParam(StringUtils.isNotBlank(request.getStatusName()), "statusName", request.getStatusName())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    @Override
    public JSONObject listCycleBills(listCycleBillsRequest request) {
        JSONObject tokenJson = zsGetToken(managerUserId, "bss");
        String resultStr = new IotHttpClient.Builder()
                .connMethod(IotHttpClient.ConnMethod.HTTPS_GET)
                .url(ZS_MANAGER_SERVICE_URL + "/api/v1/bss/bills/listCycleBills")
                //封装公共请求头
                .addHeaderParam("authorization", tokenJson.getString("token"))
                .addHeaderParam("moduleType", "bss")
                .addHeaderParam("subject", tokenJson.getString("subject"))
                .addHeaderParam("sessionId", tokenJson.getString("session_id"))
                .addHeaderParam("X-XSRF-TOKEN", tokenJson.getString("xsrf"))
                .addHeaderParam("Cookie", "XSRF-TOKEN=" + tokenJson.getString("xsrf"))
                .addBodyParam("pagenum", Integer.valueOf(request.getPageNum()).toString())
                .addBodyParam("pagesize", Integer.valueOf(request.getPageSize()).toString())
                .addBodyParam("groupByFlag", request.getGroupByFlag())
                .addBodyParam(StringUtils.isNotBlank(request.getCustomerName()), "customerName", request.getCustomerName())
                .addBodyParam(StringUtils.isNotBlank(request.getSubscriptionType()), "subscriptionType", request.getSubscriptionType())
                .addBodyParam(StringUtils.isNotBlank(request.getPriceType()), "priceType", request.getPriceType())
                .build().execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            return result.getJSONObject("data");
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.FAIL, "请求失败");
        }
    }

    public JSONObject zsGetToken(String userId, String type) {
        Object cacheResult = redisTemplate.opsForValue().get("token" + type + userId);
        if (cacheResult != null && cacheResult instanceof JSONObject) {
            return (JSONObject) cacheResult;
        }
        JSONObject queryPlain = new JSONObject();
        queryPlain.put("userId", userId);
        queryPlain.put("moduleType", type);
        queryPlain.put("timestamp", System.currentTimeMillis() / 1000);
        String encryptedData;
        try {
            queryPlain.put("sign", Sm2Util.sign(ZsCSConstants.zsServerPrivate, queryPlain));
            encryptedData = Sm2Util.encrypt(ZsCSConstants.zsPublic, queryPlain.toJSONString());
        } catch (Exception e) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.SM2_ENCRYPTION_FAIL);
        }
        IotHttpClient httpClient = new IotHttpClient.Builder()
                .mediaType(IotHttpClient.MediaType.APPLICATION_JSON)
                .connMethod(IotHttpClient.ConnMethod.HTTPS_POST)
                .url(ZS_SERVICE_URL + "/api/v1/customize/users/getManagerToken")
                .addBodyParam("userInfo", encryptedData).build();
        String resultStr = httpClient.execute();
        JSONObject result = JSON.parseObject(resultStr);
        if (result != null && Integer.valueOf(200).equals(result.getInteger("code"))) {
            JSONObject retJson = new JSONObject();
            retJson.put("subject", result.get("subject"));
            retJson.put("session_id", result.get("session_id"));
            retJson.put("token", result.get("token"));
            retJson.put("xsrf", httpClient.getXsrf());
            redisTemplate.opsForValue().set("token" + type + userId, retJson, 110, TimeUnit.SECONDS);
            return retJson;
        } else if (result != null) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.AI_PLATFORM_ERROR, result.getString("message"));
        } else {
            throw new BadRequestException(ResultCodeEnum.AUDIT_FAIL, "获取智算token失败");
        }
    }


}
