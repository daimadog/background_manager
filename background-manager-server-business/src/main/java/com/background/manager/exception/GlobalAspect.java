package com.background.manager.exception;

import cn.hutool.core.util.ObjectUtil;
import com.background.manager.exception.utils.ThrowableUtil;
import com.background.manager.model.BackgroundUser;
import com.background.manager.response.ApiResponse;
import com.background.manager.util.LoginHelper;
import com.background.manager.util.SystemLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.lang.reflect.Method;

/**
 * @Description: 全局操作日志切面模型
 * @Author: 杜黎明
 * @Date: 2022/10/17 14:04:00
 * @Version: 1.0.0
 */
@Slf4j
@Aspect
@Component
public class GlobalAspect {

    private static final Logger exlog= LoggerFactory.getLogger("EXLOG");

    private static final Logger endlog=LoggerFactory.getLogger("ENDLOG");

    private static final Logger errorlog=LoggerFactory.getLogger("ERRORLOG");

    /** 匿名用户ID **/
    private static final String NO_AUTH_OPERATOR_ID = "-1";

    /**
     * 定义AOP签名,拦截的文件
     */
    @Pointcut("execution(public * com.background.manager.controller..*.*(..))")
    public void systemLog1() {
    }

    /**
     * 定义AOP签名,排除拦截
     */
    @Pointcut("execution(public * com.background.manager.controller.LogController.*(..))")
    public void systemLog2() {
    }

    /**
     * 定义AOP签名,排除拦截
     */
    @Pointcut("execution(public * com.background.manager.controller.LoginController.*(..))")
    public void systemLog3() {
    }

    /**
     * 配置切入点
     */
    @Pointcut("systemLog1()&&!systemLog2()&&!systemLog3()")
    public void systemLog() {
    }

    /**
     * 切面日志环绕打印，当方法异常导致无法正常打印（跳转到AfterThrowing）
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("systemLog()")
    public Object surround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Method method = ((MethodSignature) (pjp.getSignature())).getMethod();
        String allPathMethod = method.getDeclaringClass().getName().concat(".").concat(method.getName()).concat("()");
        BackgroundUser loginUser;
        try {
            loginUser=LoginHelper.getLoginUser();
        } catch (Exception e) {
            loginUser=null;
        }
        String operatorId= ObjectUtil.isNotNull(loginUser)?loginUser.getLoginId():NO_AUTH_OPERATOR_ID;
        exlog.info("调用开始-->{}：操作人id标识：{} --->>>请求参数：[{}]",allPathMethod,operatorId, StringUtils.join(args," ; "));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = pjp.proceed(); //调用目标函数执行
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        SystemLogUtil.startRequest(method);
            // 如果是 AjaxJson
            if (proceed instanceof ApiResponse) {
                SystemLogUtil.endRequest((ApiResponse) proceed, method, true, null);
            } else if (proceed instanceof String) {
                // 如果是 String
                SystemLogUtil.endRequest(ApiResponse.ok(String.valueOf(proceed)), method, true, null);
            }
            // 如果都不是
            else {
                SystemLogUtil.endRequest(ApiResponse.ok(String.valueOf(proceed)), method, true, null);
            }
        endlog.info("调用结束--> {} : 操作人id标识:{} --->>> 耗时:{}ms",allPathMethod,operatorId,totalTimeMillis);
        return proceed;
        }

    @AfterThrowing(pointcut = "systemLog()",throwing = "e")
    public void doAfterThrowing(JoinPoint pjp, Throwable e){
        SystemLogUtil.writeExceptionOperation(pjp,e);
        Method method = ((MethodSignature) (pjp.getSignature())).getMethod();
        String allPathMethod  = method.getDeclaringClass().getName().concat(".").concat(method.getName()).concat("()");
        BackgroundUser loginUser;
        try {
            loginUser=LoginHelper.getLoginUser();
        } catch (Exception exception) {
            loginUser=null;
        }
        String operationId= ObjectUtil.isNotNull(loginUser)?loginUser.getLoginId():NO_AUTH_OPERATOR_ID;
        errorlog.error("调用异常--> {} : 操作人id标识:{} --->>> 返回值:{}",allPathMethod, operationId, ThrowableUtil.getStackTrace(e));
    }


}
