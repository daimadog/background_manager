package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.background.manager.business.IBusinessClient;
import com.background.manager.business.dto.IBackgroundUserDTO;
import com.background.manager.constant.StatusConstant;
import com.background.manager.convert.BackgroundWorkOrderMessageConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundWorkOrderMessageMapper;
import com.background.manager.model.dto.request.workOrder.ReplyWorkOrderMessageRequest;
import com.background.manager.model.work.BackgroundWorkOrder;
import com.background.manager.model.work.BackgroundWorkOrderMessage;
import com.background.manager.service.BackgroundWorkOrderMessageService;
import com.background.manager.service.BackgroundWorkOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Description: 工单消息接口实现类
 * @Author: 杜黎明
 * @Date: 2023/01/29 10:26:18
 * @Version: 1.0.0
 */
@Service
public class BackgroundWorkOrderMessageServiceImpl extends ServiceImpl<BackgroundWorkOrderMessageMapper,BackgroundWorkOrderMessage> implements BackgroundWorkOrderMessageService {

    @Resource
    private BackgroundWorkOrderService backgroundWorkOrderService;

    @Resource
    private BackgroundWorkOrderMessageConvertor backgroundWorkOrderMessageConvertor;

    @Resource
    private IBusinessClient iBusinessClient;

    @Override
    public void replyMessage(ReplyWorkOrderMessageRequest request) {
        //校验工单是否存在
        BackgroundWorkOrder backgroundWorkOrder = backgroundWorkOrderService.getOne(
                new LambdaQueryWrapper<BackgroundWorkOrder>()
                .eq(BackgroundWorkOrder::getId, request.getWorkOrderId()));
        if (ObjectUtils.isEmpty(backgroundWorkOrder)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.WORK_ORDER_NOT_EXIST_ERROR);
        }
        BackgroundWorkOrderMessage backgroundWorkOrderMessage=backgroundWorkOrderMessageConvertor.toWorkOrderMessage(request);
        backgroundWorkOrderMessage.setDirection(StatusConstant.DIRECTION_OPERATION_TO_USER);
        if (StpUtil.isLogin()){
            backgroundWorkOrderMessage.setCreateBy(StpUtil.getLoginIdAsString());
        }
        backgroundWorkOrderMessage.setCreateTime(LocalDateTime.now());
        this.save(backgroundWorkOrderMessage);
        //更新工单处理人以及处理时间
        IBackgroundUserDTO backgroundUser = iBusinessClient.getUserByLoginId(StpUtil.getLoginIdAsString());
        backgroundWorkOrder.setHandleBy(backgroundUser.getUserName());
        backgroundWorkOrder.setHandleTime(LocalDateTime.now());
        backgroundWorkOrderService.updateById(backgroundWorkOrder);
    }
}
