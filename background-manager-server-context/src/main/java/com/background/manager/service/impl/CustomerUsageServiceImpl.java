package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.resource.ResourceConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.model.dto.request.resource.AddCustomerUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyCustomerUsageRequest;
import com.background.manager.model.dto.response.resource.CustomerUsageDTO;
import com.background.manager.mapper.CustomerUsageMapper;
import com.background.manager.model.resource.CustomerUsage;
import com.background.manager.service.CustomerUsageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUsageServiceImpl extends ServiceImpl<CustomerUsageMapper,CustomerUsage> implements CustomerUsageService {

    @Resource
    private  ResourceConvertor resourceConvertor;

    @Override
    public void addCustomerUsage(AddCustomerUsageRequest request) {
        CustomerUsage customerUsage = resourceConvertor.toCustomerUsage(request);
        this.save(customerUsage);
    }

    @Override
    public List<CustomerUsageDTO> listCustomerUsage(Integer type) {
        List<CustomerUsage> customerUsageList = this.list(new LambdaQueryWrapper<CustomerUsage>()
                .eq(ObjectUtil.isNotNull(type), CustomerUsage::getType, type));
        if (CollectionUtil.isNotEmpty(customerUsageList)){
            return resourceConvertor.toCustomerUsageDTOS(customerUsageList);
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteCustomerUsage(Long id) {
        CustomerUsage customerUsage = this.getOne(new LambdaQueryWrapper<CustomerUsage>()
                .eq(CustomerUsage::getId, id));
        if (ObjectUtil.isNull(customerUsage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CUSTOMER_USAGE_ENTITY_NOT_EXIST);
        }
        this.removeById(customerUsage);
    }

    @Override
    public void modifyCustomerUsage(ModifyCustomerUsageRequest request) {
        CustomerUsage customerUsage = this.getOne(new LambdaQueryWrapper<CustomerUsage>()
                .eq(CustomerUsage::getId, request.getId()));
        if (ObjectUtil.isNull(customerUsage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CUSTOMER_USAGE_ENTITY_NOT_EXIST);
        }
        this.updateById(resourceConvertor.toCustomerUsage(request));
    }


}
