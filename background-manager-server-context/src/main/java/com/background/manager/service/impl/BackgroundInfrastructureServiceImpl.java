package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.BackgroundInfrastructureConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundInfrastructureMapper;
import com.background.manager.model.BackgroundInfrastructure;
import com.background.manager.model.dto.request.style.ListInfrastructureRequest;
import com.background.manager.model.dto.request.style.ModifyInfrastructureRequest;
import com.background.manager.model.dto.response.style.BackgroundInfrastructureDTO;
import com.background.manager.service.BackgroundInfrastructureService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BackgroundInfrastructureServiceImpl extends ServiceImpl<BackgroundInfrastructureMapper, BackgroundInfrastructure> implements BackgroundInfrastructureService {

    private final BackgroundInfrastructureConvertor backgroundInfrastructureConvertor;

    @Override
    public List<BackgroundInfrastructureDTO> listInfrastructure(ListInfrastructureRequest request) {
        List<BackgroundInfrastructure> backgroundInfrastructureList = this.list(
                new LambdaQueryWrapper<BackgroundInfrastructure>()
                        .like(StringUtils.isNotBlank(request.getName()), BackgroundInfrastructure::getName, request.getName())
                        .orderByDesc(BackgroundInfrastructure::getModifier)
        );
        if (CollectionUtil.isNotEmpty(backgroundInfrastructureList)){
            return backgroundInfrastructureConvertor.toBackgroundInfrastructureDTOS(backgroundInfrastructureList);
        }
            return new ArrayList<>();
    }

    @Override
    public void modifyInfrastructure(ModifyInfrastructureRequest request) {
        BackgroundInfrastructure backgroundInfrastructure = this.getOne(new LambdaQueryWrapper<BackgroundInfrastructure>()
                .eq(BackgroundInfrastructure::getId, request.getId())
        );
        if (ObjectUtil.isNull(backgroundInfrastructure)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        BackgroundInfrastructure modifyBackgroundInfrastructure = backgroundInfrastructureConvertor.toBackgroundInfrastructure(request);
        modifyBackgroundInfrastructure.setUpdateTime(LocalDateTime.now());
        this.updateById(modifyBackgroundInfrastructure);
    }

}
