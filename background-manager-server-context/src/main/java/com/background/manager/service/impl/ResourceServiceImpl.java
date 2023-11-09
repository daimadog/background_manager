package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.resource.ResourceConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.resource.AddResourceRequest;
import com.background.manager.model.dto.request.resource.ListResourceRequest;
import com.background.manager.model.dto.request.resource.ModifyResourceRequest;
import com.background.manager.model.dto.response.resource.ResourceDigestDTO;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.ClusterResourceMapper;
import com.background.manager.model.resource.ClusterResource;
import com.background.manager.service.ClusterResourceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<ClusterResourceMapper, ClusterResource> implements ClusterResourceService {

    @Resource
    private  ResourceConvertor resourceConvertor;

    @Override
    public String add(AddResourceRequest request) {
        ClusterResource clusterResource=resourceConvertor.toResource(request);
        if (ObjectUtil.isNotNull(clusterResource)){
            this.save(clusterResource);
        }
        return request.getResourceName();
    }

    @Override
    public void modify(ModifyResourceRequest request) {
        ClusterResource resource = this.getOne(new LambdaQueryWrapper<ClusterResource>().eq(ClusterResource::getId, request.getId()));
        if (ObjectUtil.isNull(resource)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.RESOURCE_ENTITY_NOT_EXIST);
        }
        ClusterResource modifyResource = resourceConvertor.toResource(request);
        this.updateById(modifyResource);
    }

    @Override
    public void delete(Long id) {
        ClusterResource resource = this.getOne(new LambdaQueryWrapper<ClusterResource>().eq(ClusterResource::getId, id));
        if (ObjectUtil.isNull(resource)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.RESOURCE_ENTITY_NOT_EXIST);
        }
        this.removeById(resource);
    }

    @Override
    public ResourceDigestDTO findResource(Long id) {
        ClusterResource resource = this.getOne(new LambdaQueryWrapper<ClusterResource>().eq(ClusterResource::getId, id));
        if (ObjectUtil.isNull(resource)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.RESOURCE_ENTITY_NOT_EXIST);
        }
        return resourceConvertor.toResourceDigestDTO(resource);
    }

    @Override
    public List<ResourceDigestDTO> listQuery(ListResourceRequest request) {
        List<ClusterResource> resourceList = this.list(new LambdaQueryWrapper<ClusterResource>()
                .eq(ObjectUtil.isNotNull(request.getType()), ClusterResource::getType, request.getType())
                .eq(StringUtils.isNotBlank(request.getResourceIndex()), ClusterResource::getResourceIndex, request.getResourceIndex())
                .like(StringUtils.isNotBlank(request.getResourceName()), ClusterResource::getResourceName, request.getResourceName()));
        if (CollectionUtil.isNotEmpty(resourceList)){
            return resourceConvertor.toResourceDigestDTOS(resourceList);
        }
        return new ArrayList<>();
    }

}
