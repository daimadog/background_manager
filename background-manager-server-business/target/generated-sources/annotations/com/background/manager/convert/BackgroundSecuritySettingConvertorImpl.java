package com.background.manager.convert;

import com.background.manager.dto.request.user.ModifyBackgroundSecuritySettingRequest;
import com.background.manager.dto.response.user.BackgroundSecuritySettingDTO;
import com.background.manager.model.BackgroundSecuritySetting;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:24+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class BackgroundSecuritySettingConvertorImpl implements BackgroundSecuritySettingConvertor {

    @Override
    public BackgroundSecuritySettingDTO toBackgroundSecuritySettingDTO(BackgroundSecuritySetting backgroundSecuritySetting) {
        if ( backgroundSecuritySetting == null ) {
            return null;
        }

        BackgroundSecuritySettingDTO backgroundSecuritySettingDTO = new BackgroundSecuritySettingDTO();

        backgroundSecuritySettingDTO.setId( backgroundSecuritySetting.getId() );
        backgroundSecuritySettingDTO.setPasswordLength( backgroundSecuritySetting.getPasswordLength() );
        backgroundSecuritySettingDTO.setFailNum( backgroundSecuritySetting.getFailNum() );
        backgroundSecuritySettingDTO.setLockTime( backgroundSecuritySetting.getLockTime() );

        return backgroundSecuritySettingDTO;
    }

    @Override
    public BackgroundSecuritySetting toBackgroundSecuritySetting(ModifyBackgroundSecuritySettingRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundSecuritySetting backgroundSecuritySetting = new BackgroundSecuritySetting();

        backgroundSecuritySetting.setId( request.getId() );
        backgroundSecuritySetting.setPasswordLength( request.getPasswordLength() );
        backgroundSecuritySetting.setFailNum( request.getFailNum() );
        backgroundSecuritySetting.setLockTime( request.getLockTime() );

        return backgroundSecuritySetting;
    }
}
