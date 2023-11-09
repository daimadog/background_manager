package com.background.manager.util;

import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.background.manager.constants.UserStatusConstant;
import com.background.manager.model.BackgroundOperationLog;
import com.background.manager.handler.SystemLog;
import com.background.manager.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Description: 系统日志工具类
 * @Author: 杜黎明
 * @Date: 2022/10/17 14:41:31
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class SystemLogUtil {
    @Autowired
    private static SystemLog systemLog;

    @Autowired
    public void setSpApilogMapper(SystemLog systemLog) {
        SystemLogUtil.systemLog = systemLog;
    }

    private static final Integer REQUEST_STATUS_SUCCESS=0;

    private static final Integer REQUEST_STATUS_FAIL=1;

    /**
     * 请求开始时调用，开始计时
     */
    public static void startRequest(Method method) {
        HttpServletRequest request = SpringMVCUtil.getRequest();
        String ip = IpUtils.getIpAddr(request);
        String address = AddressUtils.getRealAddressByIP(ip);
        BackgroundOperationLog backgroundOperationLog = BackgroundOperationLog.builder()
                .requestApi(request.getRequestURI())
                .requestType(request.getMethod())
                .creatorIp(ip)
                .creatorAddress(address)
                .startTime(LocalDateTime.now())
                .build();
        //获取操作方法名称
        String operationName = ClassUtils.getAnnotation(method, ApiOperation.class).value();
        if (StringUtils.isNotBlank(operationName)){
            backgroundOperationLog.setRequestName(operationName);
        }
        String parameters = method.getParameters().toString();
        if (StringUtils.isNotBlank(parameters)){
            backgroundOperationLog.setRequestParam(parameters);
        }
        if (StpUtil.isLogin()){
            backgroundOperationLog.setCreator(StpUtil.getLoginIdAsString());
            backgroundOperationLog.setRequestToken(StpUtil.getTokenValue());
        }
        request.setAttribute(UserStatusConstant.APILOG_OBJ_SAVE_KEY, backgroundOperationLog);
    }

    /**
     * 请求结束时调用，结束计时
     */
    public static void endRequest(ApiResponse response,Method method,Boolean requestStatus,String errorMsg) {
        HttpServletRequest request = SpringMVCUtil.getRequest();
        BackgroundOperationLog backgroundOperationLog = (BackgroundOperationLog) request.getAttribute(UserStatusConstant.APILOG_OBJ_SAVE_KEY);
        if (ObjectUtils.isEmpty(backgroundOperationLog)) {
            SystemLogUtil.startRequest(method);
            backgroundOperationLog = (BackgroundOperationLog) request.getAttribute(UserStatusConstant.APILOG_OBJ_SAVE_KEY);
        }
        try {
            if (requestStatus){
                backgroundOperationLog.setRequestStatus(REQUEST_STATUS_SUCCESS);
                backgroundOperationLog.setResMsg("操作成功");
            }else {
                backgroundOperationLog.setRequestStatus(REQUEST_STATUS_FAIL);
                backgroundOperationLog.setResMsg(errorMsg);
            }
            backgroundOperationLog.setResCode(response.getCode());
            backgroundOperationLog.setResJson(new ObjectMapper().writeValueAsString(response));
            backgroundOperationLog.setEndTime(LocalDateTime.now());
            //记录花费时间(ms)
            int costTime= (int) ((Duration.between(backgroundOperationLog.getStartTime(),backgroundOperationLog.getEndTime()).toMillis()));
            backgroundOperationLog.setCostTime(costTime);
            //res字符串过长时禁止写入
            if (backgroundOperationLog.getResJson().length() > 50000) {
                backgroundOperationLog.setResJson("{\"msg\": \"数据过长，无法写入 (length=" + backgroundOperationLog.getResJson().length() + ")\"}");
            }
            systemLog.systemLog(request, backgroundOperationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeExceptionOperation(JoinPoint pjp, Throwable e) {
        Method method=((MethodSignature)pjp.getSignature()).getMethod();
        HttpServletRequest request = SpringMVCUtil.getRequest();
        String ip = IpUtils.getIpAddr(request);
        String address = AddressUtils.getRealAddressByIP(ip);
        BackgroundOperationLog backgroundOperationLog = BackgroundOperationLog.builder()
                .requestApi(request.getRequestURI())
                .requestType(request.getMethod())
                .creatorIp(ip)
                .creatorAddress(address)
                .startTime(LocalDateTime.now())
                .build();
        //获取controller接口的操作日志名称
        String operationName = ClassUtils.getAnnotation(method, ApiOperation.class).value();
        if (StringUtils.isNotBlank(operationName)){
            backgroundOperationLog.setRequestName(operationName);
        }
        String parameters = Arrays.toString(method.getParameters());
        if (StringUtils.isNotBlank(parameters)){
            backgroundOperationLog.setRequestParam(parameters);
        }
        if (StpUtil.isLogin()){
            backgroundOperationLog.setCreator(StpUtil.getLoginIdAsString());
            backgroundOperationLog.setRequestToken(StpUtil.getTokenValue());
        }
        //封装response
        backgroundOperationLog.setRequestStatus(REQUEST_STATUS_FAIL);
        backgroundOperationLog.setResMsg("失败原因：{"+e.getMessage()+"}");
        backgroundOperationLog.setResCode("200");
        backgroundOperationLog.setResJson(e.toString());
        backgroundOperationLog.setEndTime(LocalDateTime.now());
        int costTime= (int) ((Duration.between(backgroundOperationLog.getStartTime(),backgroundOperationLog.getEndTime()).toMillis()+0.0)/1000);
        backgroundOperationLog.setCostTime(costTime);
        systemLog.systemLog(request, backgroundOperationLog);
    }

}