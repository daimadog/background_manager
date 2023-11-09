package com.background.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.TSysUserConvertor;
import com.background.manager.dto.request.user.ResourceApplicationFormDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.ResourceApplicationFormMapper;
import com.background.manager.model.ResourceApplicationForm;
import com.background.manager.model.TSysUser;
import com.background.manager.service.ResourceApplicationFormService;
import com.background.manager.service.TSysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 资源申请接口实现类
 * @Author: 杜黎明
 * @Date: 2023/03/13 14:43:28
 * @Version: 1.0.0
 */
@Service
public class ResourceApplicationFormServiceImpl extends ServiceImpl<ResourceApplicationFormMapper, ResourceApplicationForm> implements ResourceApplicationFormService {

    @Resource
    private TSysUserService tSysUserService;

    @Resource
    private TSysUserConvertor tSysUserConvertor;

    @Override
    public ResourceApplicationFormDTO getResourceApplicationForm(String userId) {
        TSysUser sysUser = tSysUserService.getById(userId);
        if (ObjectUtil.isNull(sysUser)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        ResourceApplicationForm resourceApplicationForm = this.getOne(
                new LambdaQueryWrapper<ResourceApplicationForm>()
                .eq(ResourceApplicationForm::getUserId, userId));
        if (ObjectUtil.isNull(resourceApplicationForm)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.RESOURCE_APPLICATION_FORM_NOT_EXIST);
        }
        ResourceApplicationFormDTO resourceApplicationFormDTO = tSysUserConvertor.toResourceApplicationFormDTO(resourceApplicationForm);
        return resourceApplicationFormDTO;
    }

    @Override
    public void modifyResourceApplicationForm(String userId, String conclusion) {
        TSysUser sysUser = tSysUserService.getById(userId);
        if (ObjectUtil.isNull(sysUser)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        ResourceApplicationForm resourceApplicationForm = this.getOne(
                new LambdaQueryWrapper<ResourceApplicationForm>()
                        .eq(ResourceApplicationForm::getUserId, userId));
        if (ObjectUtil.isNull(resourceApplicationForm)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.RESOURCE_APPLICATION_FORM_NOT_EXIST);
        }
    }


}
