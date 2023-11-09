package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.UserLoginConfigurationConvertor;
import com.background.manager.dto.request.user.modifyUserLoginConfigurationRequest;
import com.background.manager.dto.response.user.UserLoginConfigurationDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.UserLoginConfigurationMapper;
import com.background.manager.model.UserLoginConfiguration;
import com.background.manager.service.UserLoginConfigurationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Description: 用户登录配置接口
 * @Author: 杜黎明
 * @Date: 2023/04/03 16:43:29
 * @Version: 1.0.0
 */
@Service
public class UserLoginConfigurationServiceImpl extends ServiceImpl<UserLoginConfigurationMapper, UserLoginConfiguration> implements UserLoginConfigurationService {

    @Resource
    private UserLoginConfigurationConvertor userLoginConfigurationConvertor;

    @Override
    public void modifyUserLoginConfiguration(modifyUserLoginConfigurationRequest request) {
        UserLoginConfiguration userLoginConfiguration = this.getOne(
                new LambdaQueryWrapper<UserLoginConfiguration>()
                        .eq(UserLoginConfiguration::getId, request.getId()));
        if (ObjectUtil.isNull(userLoginConfiguration)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        UserLoginConfiguration modifyUserEnterpriseCertification = userLoginConfigurationConvertor.toUserLoginConfiguration(request);
        if (StpUtil.isLogin()){
            modifyUserEnterpriseCertification.setModifier(StpUtil.getLoginIdAsString());
        }
        modifyUserEnterpriseCertification.setModifyTime(LocalDateTime.now());
        this.updateById(modifyUserEnterpriseCertification);
    }

    @Override
    public UserLoginConfigurationDTO getUserLoginConfiguration() {
        UserLoginConfiguration userLoginConfiguration = this.getOne(
                new LambdaQueryWrapper<UserLoginConfiguration>());
        if (ObjectUtil.isNull(userLoginConfiguration)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return userLoginConfigurationConvertor.toUserLoginConfigurationDTO(userLoginConfiguration);
    }

}
