package com.background.manager.service;

import com.background.manager.dto.request.user.ModifyBackgroundSecuritySettingRequest;
import com.background.manager.dto.request.user.ProcessUserRequest;
import com.background.manager.dto.response.user.BackgroundSecuritySettingDTO;
import com.background.manager.model.BackgroundSecuritySetting;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BackgroundSecuritySettingService extends IService<BackgroundSecuritySetting> {

    BackgroundSecuritySettingDTO getBackgroundSecuritySetting();

    void modify(ModifyBackgroundSecuritySettingRequest request);

}
