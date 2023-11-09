package com.background.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.BackgroundSecuritySettingConvertor;
import com.background.manager.dto.request.user.ModifyBackgroundSecuritySettingRequest;
import com.background.manager.dto.response.user.BackgroundSecuritySettingDTO;
import com.background.manager.dto.response.user.PasswordEliminationDTO;
import com.background.manager.dto.response.user.PasswordRuleDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.security.BackgroundSecuritySettingMapper;
import com.background.manager.model.BackgroundSecuritySetting;
import com.background.manager.service.BackgroundSecuritySettingService;
import com.background.manager.service.PasswordEliminationService;
import com.background.manager.service.PasswordRuleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BackgroundSecuritySettingServiceImpl extends ServiceImpl<BackgroundSecuritySettingMapper, BackgroundSecuritySetting> implements BackgroundSecuritySettingService {

    private final BackgroundSecuritySettingConvertor backgroundSecuritySettingConvertor;

    private final PasswordEliminationService passwordEliminationService;

    private final PasswordRuleService passwordRuleService;


    @Override
    public BackgroundSecuritySettingDTO getBackgroundSecuritySetting() {
        BackgroundSecuritySetting backgroundSecuritySetting = this.getOne(new LambdaQueryWrapper<BackgroundSecuritySetting>());
        if (ObjectUtil.isNull(backgroundSecuritySetting)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        BackgroundSecuritySettingDTO backgroundSecuritySettingDTO=backgroundSecuritySettingConvertor.toBackgroundSecuritySettingDTO(backgroundSecuritySetting);
        //封装常用密码
        List<PasswordEliminationDTO> passwordEliminationDTOS=passwordEliminationService.getPasswordElimination();
        backgroundSecuritySettingDTO.setPasswordEliminationList(passwordEliminationDTOS);
        //封装密码规则
        List<PasswordRuleDTO> passwordRuleDTOS=passwordRuleService.getPasswordRule();
        backgroundSecuritySettingDTO.setPasswordRuleList(passwordRuleDTOS);
        return backgroundSecuritySettingDTO;
    }

    @Override
    @Transactional(rollbackFor = BadRequestException.class)
    public void modify(ModifyBackgroundSecuritySettingRequest request) {
        BackgroundSecuritySetting backgroundSecuritySetting = this.getOne(new LambdaQueryWrapper<BackgroundSecuritySetting>()
                .eq(BackgroundSecuritySetting::getId, request.getId())
        );
        if (ObjectUtil.isNull(backgroundSecuritySetting)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        BackgroundSecuritySetting modifyBackgroundSecuritySetting = backgroundSecuritySettingConvertor.toBackgroundSecuritySetting(request);
        this.updateById(modifyBackgroundSecuritySetting);
        //修改常用密码剔除
        passwordEliminationService.modify(request.getPasswordList());
        //修改密码规则
        passwordRuleService.modify(request.getRuleList());
    }

}
