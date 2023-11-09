package com.background.manager.service;

import com.background.manager.model.dto.request.workOrder.ReplyWorkOrderMessageRequest;
import com.background.manager.model.work.BackgroundWorkOrderMessage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @Description: 工单消息接口类
 * @Author: 杜黎明
 * @Date: 2023/01/29 10:24:51
 * @Version: 1.0.0
 */
public interface BackgroundWorkOrderMessageService extends IService<BackgroundWorkOrderMessage> {

    /**
     * Description: 运营人员回复工单消息
     * @param request 回复工单消息请求体
     * @author 杜黎明
     * @date 2023/01/29 10:39:35
     */
    void replyMessage(ReplyWorkOrderMessageRequest request);
}
