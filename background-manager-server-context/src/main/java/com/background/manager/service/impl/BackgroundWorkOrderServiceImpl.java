package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.background.manager.business.IBusinessClient;
import com.background.manager.business.dto.IBackgroundUserDTO;
import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.constant.StatusConstant;
import com.background.manager.convert.BackgroundWorkOrderConvertor;
import com.background.manager.convert.BackgroundWorkOrderMessageConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundWorkOrderMapper;
import com.background.manager.model.dto.request.workOrder.PageQueryBackgroundWorkOrderRequest;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDTO;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDigestDTO;
import com.background.manager.model.work.BackgroundWorkOrder;
import com.background.manager.model.work.BackgroundWorkOrderMessage;
import com.background.manager.service.BackgroundWorkOrderMessageService;
import com.background.manager.service.BackgroundWorkOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 工单管理接口实现类
 * @Author: 杜黎明
 * @Date: 2023/01/19 09:18:01
 * @Version: 1.0.0
 */
@Service
public class BackgroundWorkOrderServiceImpl extends ServiceImpl<BackgroundWorkOrderMapper, BackgroundWorkOrder> implements BackgroundWorkOrderService {

    @Resource
    private BackgroundWorkOrderConvertor backgroundworkOrderConvertor;
    @Resource
    private BackgroundWorkOrderMessageService backgroundWorkOrderMessageService;
    @Resource
    private BackgroundWorkOrderMessageConvertor backgroundWorkOrderMessageConvertor;
    @Resource
    private IBusinessClient iBusinessClient;

    @Override
    public IPage<BackgroundWorkOrderDigestDTO> pageQuery(PageQueryBackgroundWorkOrderRequest request) {
        Page<BackgroundWorkOrder> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(request.getTitle()),BackgroundWorkOrder::getTitle,request.getTitle())
                .eq(ObjectUtils.isNotEmpty(request.getType()),BackgroundWorkOrder::getType,request.getType())
                .eq(ObjectUtils.isNotEmpty(request.getStatus()),BackgroundWorkOrder::getStatus,request.getStatus())
                .eq(StringUtils.isNotBlank(request.getAccountId()),BackgroundWorkOrder::getAccountId,request.getAccountId())
                //1、按照工单状态进行排序，优先展示待处理的工单
                .orderByAsc(BackgroundWorkOrder::getStatus)
                //2、按照工单时间降序
                .orderByDesc(BackgroundWorkOrder::getSubmitTime)
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        //封装工单信息
        List<BackgroundWorkOrderDigestDTO> backgroundWorkOrderDigestDTOList=new ArrayList<>();
        for (BackgroundWorkOrder backgroundWorkOrder: page.getRecords()){
            BackgroundWorkOrderDigestDTO workOrderDigestDTO = backgroundworkOrderConvertor.toBackgroundWorkOrderDigestDTO(backgroundWorkOrder);
            ITsysUserDTO tsysUser = iBusinessClient.getTsysUserByAccountId(backgroundWorkOrder.getAccountId());
            workOrderDigestDTO.setUsername(tsysUser.getUsername());
            backgroundWorkOrderDigestDTOList.add(workOrderDigestDTO);
        }
        Page<BackgroundWorkOrderDigestDTO> pages=new Page<>();
        pages.setPages(page.getPages())
                .setTotal(page.getTotal())
                .setCurrent(page.getCurrent())
                .setSize(page.getSize())
                .setRecords(backgroundWorkOrderDigestDTOList);
        return pages;
    }

    @Override
    public void closeWorkOrder(String id) {
        BackgroundWorkOrder backgroundWorkOrder = this.getOne(new LambdaQueryWrapper<BackgroundWorkOrder>()
                .eq(BackgroundWorkOrder::getId, id));
        if (ObjectUtils.isEmpty(backgroundWorkOrder)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.WORK_ORDER_NOT_EXIST_ERROR);
        }
        backgroundWorkOrder.setStatus(StatusConstant.CLOSE_WORK_ORDER);
        //设置工单处理人
        IBackgroundUserDTO backgroundUser = iBusinessClient.getUserByLoginId(StpUtil.getLoginIdAsString());
        backgroundWorkOrder.setHandleBy(StpUtil.getLoginIdAsString());
        backgroundWorkOrder.setHandleTime(LocalDateTime.now());
        backgroundWorkOrder.setCompleteTime(LocalDateTime.now());
        this.updateById(backgroundWorkOrder);
    }

    @Override
    public BackgroundWorkOrderDTO findWorkOrder(String id) {
        //校验工单是否存在
        BackgroundWorkOrder backgroundWorkOrder = this.getOne(new LambdaQueryWrapper<BackgroundWorkOrder>()
                .eq(BackgroundWorkOrder::getId, id));
        if (ObjectUtils.isEmpty(backgroundWorkOrder)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.WORK_ORDER_NOT_EXIST_ERROR);
        }
        BackgroundWorkOrderDTO backgroundWorkOrderDTO=backgroundworkOrderConvertor.toBackgroundWorkOrderDTO(backgroundWorkOrder);
        String username = iBusinessClient.getTsysUserByAccountId(backgroundWorkOrder.getAccountId()).getUsername();
        backgroundWorkOrderDTO.setUsername(username);
        List<BackgroundWorkOrderMessage> backgroundWorkOrderMessageList = backgroundWorkOrderMessageService.list(
                new LambdaQueryWrapper<BackgroundWorkOrderMessage>()
                        .eq(BackgroundWorkOrderMessage::getWorkOrderId, id)
                        .orderByDesc(BackgroundWorkOrderMessage::getCreateTime)
        );
        backgroundWorkOrderDTO.setMessageList(backgroundWorkOrderMessageConvertor.toWorkOrderMessageDTOS(backgroundWorkOrderMessageList));
        return backgroundWorkOrderDTO;
    }

}
