package com.background.manager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Description: 异步线程的上下文同步
 * @Author: 杜黎明
 * @Date: 2022/10/17 16:35:29
 * @Version: 1.0.0
 */
public class ContextDecorator implements TaskDecorator {

    private static final Logger log = LoggerFactory.getLogger(ContextDecorator.class);

    @Override
    public Runnable decorate(Runnable runnable) {
        //获取主线程中的请求信息
        RequestAttributes context = RequestContextHolder.currentRequestAttributes();
        log.info(Thread.currentThread().getName());
        return () -> {
            try {
                //方法一：将主线程的请求信息设置到子线程中
                //此方法存在的缺陷：如果主线程先于子线程执行完成，则主线程结束后会销毁Request，则子线程无法拿到数据
                RequestContextHolder.setRequestAttributes(context,true);
                /**方法二：使用ThreadLocal线程全局变量来存放上下文数据*/
                log.info(Thread.currentThread().getName());
                runnable.run();
            } finally {
                //重置，防止子线程泄露
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }

}