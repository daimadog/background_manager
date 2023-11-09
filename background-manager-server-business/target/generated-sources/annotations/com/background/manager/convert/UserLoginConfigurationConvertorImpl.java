package com.background.manager.convert;

import com.background.manager.dto.request.user.modifyUserLoginConfigurationRequest;
import com.background.manager.dto.response.user.UserLoginConfigurationDTO;
import com.background.manager.model.UserLoginConfiguration;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:24+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class UserLoginConfigurationConvertorImpl implements UserLoginConfigurationConvertor {

    @Override
    public UserLoginConfiguration toUserLoginConfiguration(modifyUserLoginConfigurationRequest request) {
        if ( request == null ) {
            return null;
        }

        UserLoginConfiguration userLoginConfiguration = new UserLoginConfiguration();

        userLoginConfiguration.setId( request.getId() );
        userLoginConfiguration.setLoginFailNum( request.getLoginFailNum() );
        userLoginConfiguration.setLockTime( request.getLockTime() );

        return userLoginConfiguration;
    }

    @Override
    public UserLoginConfigurationDTO toUserLoginConfigurationDTO(UserLoginConfiguration userLoginConfiguration) {
        if ( userLoginConfiguration == null ) {
            return null;
        }

        UserLoginConfigurationDTO userLoginConfigurationDTO = new UserLoginConfigurationDTO();

        userLoginConfigurationDTO.setId( userLoginConfiguration.getId() );
        userLoginConfigurationDTO.setLoginFailNum( userLoginConfiguration.getLoginFailNum() );
        userLoginConfigurationDTO.setLockTime( userLoginConfiguration.getLockTime() );
        userLoginConfigurationDTO.setCreator( userLoginConfiguration.getCreator() );
        userLoginConfigurationDTO.setCreatorTime( userLoginConfiguration.getCreatorTime() );
        userLoginConfigurationDTO.setModifier( userLoginConfiguration.getModifier() );
        userLoginConfigurationDTO.setModifyTime( userLoginConfiguration.getModifyTime() );

        return userLoginConfigurationDTO;
    }
}
