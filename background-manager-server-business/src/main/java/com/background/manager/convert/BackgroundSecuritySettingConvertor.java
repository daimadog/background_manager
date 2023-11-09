package com.background.manager.convert;

import com.background.manager.dto.request.user.ModifyBackgroundSecuritySettingRequest;
import com.background.manager.dto.response.user.BackgroundSecuritySettingDTO;
import com.background.manager.model.BackgroundSecuritySetting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BackgroundSecuritySettingConvertor {

    BackgroundSecuritySettingDTO toBackgroundSecuritySettingDTO(BackgroundSecuritySetting backgroundSecuritySetting);

    BackgroundSecuritySetting toBackgroundSecuritySetting(ModifyBackgroundSecuritySettingRequest request);
}
