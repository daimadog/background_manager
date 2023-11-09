package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.constant.StatusConstant;
import com.background.manager.convert.BackgroundOfflineMessageConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.offlineRegistration.AddRegistrationRequest;
import com.background.manager.model.dto.request.offlineRegistration.PageQueryOfflineMessageRequest;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDTO;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDigestDTO;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundOfflineMessageMapper;
import com.background.manager.model.BackgroundOfflineMessage;
import com.background.manager.service.BackgroundOfflineMessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Description: 离线登记接口实现类
 * @Author: 杜黎明
 * @Date: 2022/11/03 09:05:15
 * @Version: 1.0.0
 */
@Service
public class BackgroundOfflineMessageServiceImpl extends ServiceImpl<BackgroundOfflineMessageMapper, BackgroundOfflineMessage>implements BackgroundOfflineMessageService {

    @Resource
    private BackgroundOfflineMessageConvertor backgroundOfflineMessageConvertor;

    @Override
    public void add(AddRegistrationRequest request) {
        BackgroundOfflineMessage backgroundOfflineMessage = backgroundOfflineMessageConvertor.toBackgroundOfflineMessage(request);
        backgroundOfflineMessage.setCreateTime(LocalDateTime.now());
        backgroundOfflineMessage.setUpdateTime(LocalDateTime.now());
        this.save(backgroundOfflineMessage);
    }

    @Override
    public IPage<OfflineMessageDigestDTO> pageQuery(PageQueryOfflineMessageRequest request) {
        Page<BackgroundOfflineMessage> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(request.getName()), BackgroundOfflineMessage::getName, request.getName())
                .like(StringUtils.isNotBlank(request.getCompany()), BackgroundOfflineMessage::getCompany, request.getCompany())
                .like(StringUtils.isNotBlank(request.getPhone()), BackgroundOfflineMessage::getPhone, request.getPhone())
                .eq(ObjectUtil.isNotNull(request.getProcessState()), BackgroundOfflineMessage::getProcessState, request.getProcessState())
                .orderByDesc(BackgroundOfflineMessage::getCreateTime)
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return  new Page<>();
        }
        Page<OfflineMessageDigestDTO> pages=new Page<>(page.getCurrent(),page.getSize());
        pages.setTotal(page.getTotal());
        pages.setPages(page.getPages());
        pages.setRecords(backgroundOfflineMessageConvertor.toOfflineMessageDTOS(page.getRecords()));
        return pages;
    }

    @Override
    public OfflineMessageDTO getOfflineMessage(Long id) {
        BackgroundOfflineMessage backgroundOfflineMessage = this.getOne(new LambdaQueryWrapper<BackgroundOfflineMessage>()
                .eq(BackgroundOfflineMessage::getId, id));
        if (ObjectUtil.isNull(backgroundOfflineMessage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return backgroundOfflineMessageConvertor.toBackgroundOfflineMessage(backgroundOfflineMessage);
    }

    @Override
    public boolean process(Long id) {
        BackgroundOfflineMessage backgroundOfflineMessage = this.getOne(new LambdaQueryWrapper<BackgroundOfflineMessage>()
                .eq(BackgroundOfflineMessage::getId, id));
        if (ObjectUtil.isNull(backgroundOfflineMessage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        backgroundOfflineMessage.setProcessState(StatusConstant.PENDED);
        backgroundOfflineMessage.setUpdateTime(LocalDateTime.now());
        return this.updateById(backgroundOfflineMessage);
    }


}
