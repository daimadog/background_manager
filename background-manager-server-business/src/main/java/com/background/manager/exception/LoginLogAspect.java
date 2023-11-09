package com.background.manager.exception;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.background.manager.constants.UserStatusConstant;
import com.background.manager.service.BackgroundLoginLogService;
import com.background.manager.service.BackgroundUserService;
import com.background.manager.util.LoginHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description: 登录日志切面模型
 * @Author: 杜黎明
 * @Date: 2023/04/13 10:35:38
 * @Version: 1.0.0
 */
@Aspect
@Component
public class LoginLogAspect {

    @Resource
    private BackgroundLoginLogService backgroundLoginLogService;
    @Resource
    private BackgroundUserService backgroundUserService;


    private static final Logger log= LoggerFactory.getLogger(LoginLogAspect.class);

    /**定义切点*/
    @Pointcut("execution(public * com.background.manager.controller.LoginController.login(..))")
    public void saveLog(){
    }

    @AfterReturning("saveLog()")
    public void loginLog(JoinPoint point){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String username="匿名用户";
        if (StpUtil.isLogin()){
            username=LoginHelper.getLoginId();
        }
        backgroundLoginLogService.saveLoginLog(request,username, UserStatusConstant.LOGIN_SUCCESS,UserStatusConstant.LOGIN_SUCCESS_MESSAGE);
    }

    @AfterThrowing(value = "saveLog()",throwing = "e")
    public void  errorLoginLog(JoinPoint point,Throwable e){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String username="匿名用户";
        Object[] args = point.getArgs();
        if (args.length!=0&&args!=null) {
            JSONObject jsonObject = JSON.parseObject(JSONUtil.toJsonStr(args[0]));
            username = jsonObject.getString("loginId");
        }
        backgroundLoginLogService.saveLoginLog(request,username, UserStatusConstant.LOGIN_FAIL,e.getMessage());
    }

}
