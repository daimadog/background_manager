package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.BackgroundLogConvertor;
import com.background.manager.dto.request.log.DeleteLoginLogRequest;
import com.background.manager.dto.request.log.PageQueryLoginLogRequest;
import com.background.manager.dto.response.log.LoginLogDigestDTO;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.BackgroundLoginLog;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundLoginLogMapper;
import com.background.manager.service.BackgroundLoginLogService;
import com.background.manager.service.BackgroundUserInfoService;
import com.background.manager.util.AddressUtils;
import com.background.manager.util.IpUtils;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 登录日志接口实现类
 * @Author: 杜黎明
 * @Date: 2022/10/13 17:44:28
 * @Version: 1.0.0
 */
@Service
public class BackgroundLoginLogServiceImpl extends ServiceImpl<BackgroundLoginLogMapper,BackgroundLoginLog> implements BackgroundLoginLogService {

    @Resource
    private BackgroundLogConvertor backgroundLogConvertor;

    @Resource
    private BackgroundUserInfoService backgroundUserInfoService;

    private final static  Integer BACKGROUND_LOGIN_LOG=0;

    private final static  Integer USER_LOGIN_LOG=1;


    @Override
    public IPage<LoginLogDigestDTO> pageQuery(PageQueryLoginLogRequest request) {
        Page<BackgroundLoginLog> logPage = this.lambdaQuery()
                .eq(ObjectUtil.isNotNull(request.getLogFlag()), BackgroundLoginLog::getLogFlag, request.getLogFlag())
                .ge(ObjectUtils.isNotEmpty(request.getStartTime()), BackgroundLoginLog::getCreateTime, request.getStartTime())
                .lt(ObjectUtils.isNotEmpty(request.getEndTime()), BackgroundLoginLog::getCreateTime, request.getEndTime())
                .eq(ObjectUtils.isNotEmpty(request.getStatus()), BackgroundLoginLog::getStatus, request.getStatus())
                /**关键词模糊匹配登录ID、登录IP、登录地址及登录信息等字段*/
                .and(StringUtils.isNotBlank(request.getKeyWord()), log ->
                         log.like(StringUtils.isNotBlank(request.getKeyWord()), BackgroundLoginLog::getLoginId, request.getKeyWord())
                        .or().like(StringUtils.isNotBlank(request.getKeyWord()), BackgroundLoginLog::getCreatorIp, request.getKeyWord())
                        .or().like(StringUtils.isNotBlank(request.getKeyWord()), BackgroundLoginLog::getCreatorAddress, request.getKeyWord())
                        .or().like(StringUtils.isNotBlank(request.getKeyWord()), BackgroundLoginLog::getMessage, request.getKeyWord()))
                .orderByDesc(BackgroundLoginLog::getCreateTime)
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(logPage.getRecords())){
            return new Page<>();
        }
        List<LoginLogDigestDTO> loginLogDigestDTOS=new ArrayList<>();
        for (BackgroundLoginLog amp:logPage.getRecords()){
            LoginLogDigestDTO dto = backgroundLogConvertor.LoginLogDigestDTO(amp);
            if (BACKGROUND_LOGIN_LOG.equals(amp.getLogFlag().intValue())){
                //运营控制台登录账号封装username字段
                LoginUserInfoDTO loginUserInfo = backgroundUserInfoService.findUserInfoByLoginId(amp.getLoginId());
                dto.setUsername(loginUserInfo.getUsername());
            }else if (USER_LOGIN_LOG.equals(amp.getLogFlag().intValue())){
                //用户控制台直接获取用户名
                dto.setUsername(amp.getLoginId());
            }
            loginLogDigestDTOS.add(dto);
        }
        IPage<LoginLogDigestDTO> pages=new Page<>(logPage.getCurrent(),logPage.getSize());
        pages.setTotal(logPage.getTotal())
                .setPages(logPage.getPages())
                .setRecords(loginLogDigestDTOS);
        return pages;
    }

    @Override
    public boolean delete(DeleteLoginLogRequest request) {
        if (CollectionUtil.isEmpty(request.getIdList())){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return this.removeBatchByIds(request.getIdList());
    }

    @Override
    public boolean clean() {
        List<BackgroundLoginLog> backgroundLoginLogs = this.list(new LambdaQueryWrapper<BackgroundLoginLog>());
        if (CollectionUtil.isEmpty(backgroundLoginLogs)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return this.removeBatchByIds(backgroundLoginLogs);
    }

    @Override
    @DS("salve")
    public void saveLoginLog(HttpServletRequest httpServletRequest, String loginIdAsString, Integer status,String message) {
        String ip= IpUtils.getIpAddr(httpServletRequest);
        String address = AddressUtils.getRealAddressByIP(ip);
        final UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        BackgroundLoginLog backgroundLoginLog=new BackgroundLoginLog();
        backgroundLoginLog.setLoginId(loginIdAsString);
        backgroundLoginLog.setBrowser(browser);
        backgroundLoginLog.setCreatorIp(ip);
        backgroundLoginLog.setCreatorAddress(address);
        backgroundLoginLog.setOperatingSystem(os);
        backgroundLoginLog.setStatus(status);
        backgroundLoginLog.setMessage(message);
        backgroundLoginLog.setLogFlag(0);
        this.save(backgroundLoginLog);
    }

}
