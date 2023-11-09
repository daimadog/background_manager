package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.background.manager.constant.StatusConstant;
import com.background.manager.convert.BackgroundContractTemplateConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundContractTemplateMapper;
import com.background.manager.model.BackgroundContractTemplate;
import com.background.manager.model.dto.request.template.CreateTemplateRequest;
import com.background.manager.model.dto.request.template.ModifyContractTemplateRequest;
import com.background.manager.model.dto.request.template.listQueryContractTemplateRequest;
import com.background.manager.model.dto.response.template.ContractTemplateDTO;
import com.background.manager.service.BackgroundContractTemplateService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackgroundContractTemplateServiceImpl extends ServiceImpl<BackgroundContractTemplateMapper, BackgroundContractTemplate> implements BackgroundContractTemplateService {

    @Resource
    private BackgroundContractTemplateConvertor backgroundContractTemplateConvertor;

    @Override
    public String createContractTemplate(CreateTemplateRequest request) {
        BackgroundContractTemplate contractTemplate = this.getOne(new LambdaQueryWrapper<BackgroundContractTemplate>()
                .eq(BackgroundContractTemplate::getName, request.getName()));
        if (ObjectUtils.isNotEmpty(contractTemplate)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_TEMPLATE_NAME_REPEAT_ERROR);
        }
        BackgroundContractTemplate backgroundContractTemplate = backgroundContractTemplateConvertor.toBackgroundContractTemplate(request);
        backgroundContractTemplate.setCreateTime(LocalDateTime.now());
        backgroundContractTemplate.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            backgroundContractTemplate.setCreator(StpUtil.getLoginIdAsString());
            backgroundContractTemplate.setModifier(StpUtil.getLoginIdAsString());
        }
        //设置当前合同模板为不可下载
        backgroundContractTemplate.setStatus(StatusConstant.CONTRACT_TEMPLATE_NOT_DOWNLOAD);
        this.save(backgroundContractTemplate);
        return backgroundContractTemplate.getName();
    }

    @Override
    public List<ContractTemplateDTO> listQuery(listQueryContractTemplateRequest request) {
        List<BackgroundContractTemplate> backgroundContractTemplates = this.list(new LambdaQueryWrapper<BackgroundContractTemplate>()
                .like(StringUtils.isNotBlank(request.getName()), BackgroundContractTemplate::getName, request.getName())
                .eq(ObjectUtils.isNotEmpty(request.getStatus()), BackgroundContractTemplate::getStatus, request.getStatus())
                .eq(ObjectUtils.isNotEmpty(request.getCreateTime()), BackgroundContractTemplate::getCreateTime, request.getCreateTime())
                .eq(ObjectUtils.isNotEmpty(request.getCreator()), BackgroundContractTemplate::getCreator, request.getCreator()));

        if (CollectionUtil.isEmpty(backgroundContractTemplates)){
            return new ArrayList<>();
        }
        return backgroundContractTemplateConvertor.toContractTemplateDTOS(backgroundContractTemplates);
    }

    @Override
    public void process(Long id) {
        BackgroundContractTemplate backgroundContractTemplate = this.getOne(new LambdaQueryWrapper<BackgroundContractTemplate>()
                .eq(BackgroundContractTemplate::getId, id));
        if (ObjectUtils.isEmpty(backgroundContractTemplate)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_TEMPLATE_NOT_EXIST_ERROR);
        }
        /**仅允许一个合同模板可下载*/
        this.update(new LambdaUpdateWrapper<BackgroundContractTemplate>()
                .eq(BackgroundContractTemplate::getStatus,StatusConstant.CONTRACT_TEMPLATE__DOWNLOAD)
                .set(BackgroundContractTemplate::getStatus,StatusConstant.CONTRACT_TEMPLATE_NOT_DOWNLOAD));
        backgroundContractTemplate.setStatus(StatusConstant.CONTRACT_TEMPLATE__DOWNLOAD);
        this.updateById(backgroundContractTemplate);
    }

    @Override
    public void modify(ModifyContractTemplateRequest request) {
        BackgroundContractTemplate backgroundContractTemplate = this.getOne(new LambdaQueryWrapper<BackgroundContractTemplate>()
                .eq(BackgroundContractTemplate::getId, request.getId()));
        if (ObjectUtils.isEmpty(backgroundContractTemplate)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_TEMPLATE_NOT_EXIST_ERROR);
        }
        BackgroundContractTemplate modifyBackgroundContractTemplate = backgroundContractTemplateConvertor.toBackgroundContractTemplate(request);
        this.updateById(modifyBackgroundContractTemplate);
    }

    @Override
    public void delete(Long id) {
        BackgroundContractTemplate backgroundContractTemplate = this.getOne(new LambdaQueryWrapper<BackgroundContractTemplate>()
                .eq(BackgroundContractTemplate::getId,id));
        if (ObjectUtils.isEmpty(backgroundContractTemplate)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_TEMPLATE_NOT_EXIST_ERROR);
        }
        this.removeById(backgroundContractTemplate);
    }

}
