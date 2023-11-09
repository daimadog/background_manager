package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.HomeConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.home.AddPartnersRequest;
import com.background.manager.model.dto.request.home.ModifyPartnersRequest;
import com.background.manager.model.dto.response.home.PartnersDTO;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundPartnersMapper;
import com.background.manager.model.BackgroundPartners;
import com.background.manager.service.BackgroundPartnersService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackgroundPartnersServiceImpl extends ServiceImpl<BackgroundPartnersMapper,BackgroundPartners> implements BackgroundPartnersService {

    @Resource
    private HomeConvertor homeConvertor;

    @Override
    public String addPartners(AddPartnersRequest request) {
        BackgroundPartners backgroundPartners = homeConvertor.toBackgroundPartners(request);
        this.save(backgroundPartners);
        return backgroundPartners.getTitle();
    }

    @Override
    public List<PartnersDTO> listPartners() {
        List<BackgroundPartners> backgroundPartnersList = this.list(new LambdaQueryWrapper<BackgroundPartners>());
        if (CollectionUtil.isNotEmpty(backgroundPartnersList)){
            return homeConvertor.toBackgroundPartnersDTOS(backgroundPartnersList);
        }
        return new ArrayList<>();
    }

    @Override
    public void deletePartners(Long id) {
        BackgroundPartners backgroundPartners = this.getOne(new LambdaQueryWrapper<BackgroundPartners>().eq(BackgroundPartners::getId, id));
        if (ObjectUtil.isNull(backgroundPartners)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.PARTNERS_ENTITY_NOT_EXIST_ERROR);
        }
        this.removeById(backgroundPartners);
    }

    @Override
    public void modifyPartners(ModifyPartnersRequest request) {
        BackgroundPartners backgroundPartners = this.getOne(new LambdaQueryWrapper<BackgroundPartners>().eq(BackgroundPartners::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundPartners)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.PARTNERS_ENTITY_NOT_EXIST_ERROR);
        }
        this.updateById(homeConvertor.toBackgroundPartners(request));
    }
}
