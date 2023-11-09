package com.background.manager.handler;

import cn.dev33.satoken.exception.NotLoginException;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 接口幂等性校验拦截器
 * @Author: 杜黎明
 * @Date: 2023/03/27 17:02:29
 * @Version: 1.0.0
 */
public class IdempotentTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
        ApiIdempotent apiIdempotent = handlerMethod.getMethod().getAnnotation(ApiIdempotent.class);
        if (apiIdempotent!=null){
            //校验token
            this.checkToken(request);
        }
         return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    private void checkToken(HttpServletRequest request) {
        String tokenValue = StringUtils.substringAfter(request.getHeader("Authorization"), " ");
        if (StringUtils.isBlank(tokenValue)){
            tokenValue = request.getParameter("Authorization");
            if (StringUtils.isBlank(tokenValue)){
                throw new NotLoginException("该账号未登录","login","-1");
            }
        }
        if (!RedisUtils.hasKey("Authorization:login:token:"+tokenValue)){
            throw  new NotLoginException(BackgroundManagementResultCodeEnum.OPERATION_REPETITIVE.getMessage(),"login","-1");
        }
        boolean delFlag = RedisUtils.deleteCommonObject("Authorization:login:token:"+tokenValue);
        if (!delFlag){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.OPERATION_REPETITIVE);
        }
    }


}
