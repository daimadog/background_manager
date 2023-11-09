package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.resource.ResourceConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.resource.AddResourceDailyUsage;
import com.background.manager.model.dto.request.resource.FindResourceDailyUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyResourceDailyUsageRequest;
import com.background.manager.model.dto.response.resource.ResourceDailyUsageDTO;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.ResourceDailyUsageMapper;
import com.background.manager.model.resource.ResourceDailyUsage;
import com.background.manager.model.resource.ResourceDailyValue;
import com.background.manager.service.ResourceDailyUsageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceDailyUsageServiceImpl extends ServiceImpl<ResourceDailyUsageMapper, ResourceDailyUsage>implements ResourceDailyUsageService {

    @Resource
    private  ResourceConvertor resourceConvertor;

    @Override
    public void add(AddResourceDailyUsage request) {
        List<ResourceDailyUsage> resourceDailyUsageList=new ArrayList<>();
        List<ResourceDailyValue> values = request.getValues();
        if (CollectionUtil.isNotEmpty(values)){
            for (ResourceDailyValue value: values){
                ResourceDailyUsage resourceDailyUsage=resourceConvertor.toResourceDailyUsage(request);
                resourceDailyUsage.setTime(value.getTime());
                resourceDailyUsage.setNumber(value.getNumber());
                resourceDailyUsage.setPercentage(value.getPercentage());
                resourceDailyUsageList.add(resourceDailyUsage);
            }
            this.saveBatch(resourceDailyUsageList);
        }
    }

    @Override
    public ResourceDailyUsageDTO findResource(FindResourceDailyUsageRequest request) {
        ResourceDailyUsageDTO result=new ResourceDailyUsageDTO();
        result.setDate(request.getDate());
        result.setType(request.getType());
        List<ResourceDailyUsage> resourceDailyUsageList = this.list(new LambdaQueryWrapper<ResourceDailyUsage>()
                .eq(ObjectUtil.isNotNull(request.getType()), ResourceDailyUsage::getType, request.getType())
                .eq(ObjectUtil.isNotNull(request.getDate()),ResourceDailyUsage::getDate,request.getDate()));
        //封装数据
        if (CollectionUtil.isNotEmpty(resourceDailyUsageList)){
            List<ResourceDailyValue> values=new ArrayList<>();
            for (ResourceDailyUsage resourceDailyUsage:resourceDailyUsageList) {
                ResourceDailyValue value=new ResourceDailyValue();
                value.setTime(resourceDailyUsage.getTime());
                value.setNumber(resourceDailyUsage.getNumber());
                value.setPercentage(resourceDailyUsage.getPercentage());
                values.add(value);
            }
        result.setValue(resourceConvertor.toResourceDailyValues(values));
    }
        return result;
    }

    @Override
    public void modifyResourceDailyUsage(ModifyResourceDailyUsageRequest request) {
        List<ResourceDailyValue> values = request.getValues();
        List<String> timeList=new ArrayList<>();
        if (CollectionUtil.isNotEmpty(values)){
            for (ResourceDailyValue resourceDailyValue: values){
                timeList.add(resourceDailyValue.getTime());
            }
        }
        List<ResourceDailyUsage> resourceDailyUsageList = this.list(new LambdaQueryWrapper<ResourceDailyUsage>()
                .eq(ResourceDailyUsage::getDate, request.getDate())
                .eq(ResourceDailyUsage::getType, request.getType())
                .in(ResourceDailyUsage::getTime,timeList));
        if (CollectionUtil.isNotEmpty(values)){
            for (ResourceDailyValue resourceDailyValue: values){
                ResourceDailyUsage modifyResourceDailyUsage = resourceDailyUsageList.stream().filter(resourceDailyUsage ->
                        resourceDailyValue.getTime().equals(resourceDailyUsage.getTime())).findFirst().get();
                modifyResourceDailyUsage.setNumber(resourceDailyValue.getNumber());
                modifyResourceDailyUsage.setPercentage(resourceDailyValue.getPercentage());
            }
        }
        this.updateBatchById(resourceDailyUsageList);
    }

    @Override
    public void deleteResourceDailyUsage(Date date) {
        List<ResourceDailyUsage> resourceDailyUsagesList = this.list(new LambdaQueryWrapper<ResourceDailyUsage>()
                .eq(ResourceDailyUsage::getDate, date));
        if (CollectionUtil.isEmpty(resourceDailyUsagesList)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.RESOURCE_DAILY_USAGE_ENTITY_NOT_EXIST);
        }
        this.removeBatchByIds(resourceDailyUsagesList);
    }


}
