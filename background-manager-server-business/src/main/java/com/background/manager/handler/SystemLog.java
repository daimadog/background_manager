package com.background.manager.handler;

import com.background.manager.model.BackgroundOperationLog;
import com.background.manager.service.BackgroundOperationLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 异步执行保存操作日志
 * @Author: 杜黎明
 * @Date: 2022/10/17 11:10:06
 * @Version: 1.0.0
 */
@Component
public class SystemLog {

    @Resource
    private BackgroundOperationLogService backgroundOperationLogService;

    @Async("taskExecutor")
    public void systemLog(HttpServletRequest request, BackgroundOperationLog backgroundOperationLog) {
        backgroundOperationLogService.save(backgroundOperationLog);
    }



}
