package com.background.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池配置，处理异步任务
 * @Author: 杜黎明
 * @Date: 2022/11/21 09:30:08
 * @Version: 1.0.0
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * 线程核心数
     */
    @Value("${thread.corePoolSize}")
    private int corePoolSize;
    /**
     * 线程最大数
     */
    @Value("${thread.maxPoolSize}")
    private int maxPoolSize;
    /**
     * 任务容量
     */
    @Value("${thread.queueCapacity}")
    private int queueCapacity;
    /**
     * 允许空闲时间，默认60
     */
    @Value("${thread.keepAlive}")
    private int keepAlive;

    @Bean()
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAlive);
        // 线程池名称 MyThread
        threadPoolTaskExecutor.setThreadNamePrefix("SA-Thread-");
        //设置拒绝策略 当线程数达到最大时，如何处理新任务
        //CallerRunsPolicy 不由线程池中线程执行，由调用者所在线程执行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //增加TaskDecorator属性的配置，拷贝主线程上下文信息
        threadPoolTaskExecutor.setTaskDecorator(new ContextDecorator());
        return threadPoolTaskExecutor;
    }

}
