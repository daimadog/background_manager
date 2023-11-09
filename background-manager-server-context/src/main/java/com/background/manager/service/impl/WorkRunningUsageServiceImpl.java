package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.resource.ResourceConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.resource.AddWorkRunningUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyWorkRunningUsageRequest;
import com.background.manager.model.dto.response.resource.WorkRunningUsageDTO;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.WorkRunningUsageMapper;
import com.background.manager.model.resource.WorkRunningUsage;
import com.background.manager.service.WorkRunningUsageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkRunningUsageServiceImpl extends ServiceImpl<WorkRunningUsageMapper, WorkRunningUsage> implements WorkRunningUsageService {

    @Resource
    private ResourceConvertor resourceConvertor;

    @Override
    public String add(AddWorkRunningUsageRequest request) {
        WorkRunningUsage workRunningUsage=resourceConvertor.toWorkRunningUsage(request);
        this.save(workRunningUsage);
        return workRunningUsage.getName();
    }

    @Override
    public WorkRunningUsageDTO getWorkRunningUsage(Long id) {
        WorkRunningUsage workRunningUsage = this.getOne(new LambdaQueryWrapper<WorkRunningUsage>()
                .eq(WorkRunningUsage::getId, id));
        if (ObjectUtil.isNull(workRunningUsage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.WORK_RUNNING_USAGE_ENTITY_EXIST);
        }
        return resourceConvertor.toWorkRunningUsageDTO(workRunningUsage);
    }

    @Override
    public List<WorkRunningUsageDTO> listWorkRunningUsage() {
        List<WorkRunningUsage> workRunningUsageList = this.list(new LambdaQueryWrapper<WorkRunningUsage>());
        if (CollectionUtil.isNotEmpty(workRunningUsageList)){
            return resourceConvertor.toWorkRunningUsageDTOList(workRunningUsageList);
        }
        return new ArrayList<>();
    }

    @Override
    public void modifyWorkRunningUsage(ModifyWorkRunningUsageRequest request) {
        WorkRunningUsage workRunningUsage = this.getOne(new LambdaQueryWrapper<WorkRunningUsage>()
                .eq(WorkRunningUsage::getId, request.getId()));
        if (ObjectUtil.isNull(workRunningUsage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.WORK_RUNNING_USAGE_ENTITY_EXIST);
        }
        this.updateById(resourceConvertor.toWorkRunningUsage(request));
    }

    @Override
    public void deleteWorkRunningUsage(Long id) {
        WorkRunningUsage workRunningUsage = this.getOne(new LambdaQueryWrapper<WorkRunningUsage>()
                .eq(WorkRunningUsage::getId, id));
        if (ObjectUtil.isNull(workRunningUsage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.WORK_RUNNING_USAGE_ENTITY_EXIST);
        }
        this.removeById(workRunningUsage);
    }

}
