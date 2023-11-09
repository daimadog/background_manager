package com.background.manager.convert;

import com.background.manager.business.dto.IBackgroundUserDTO;
import com.background.manager.dto.request.user.AddUserRequest;
import com.background.manager.model.BackgroundUser;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:24+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class BackgroundUserConvertorImpl implements BackgroundUserConvertor {

    @Override
    public BackgroundUser toBackgroundUser(AddUserRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundUser backgroundUser = new BackgroundUser();

        backgroundUser.setLoginId( request.getLoginId() );
        backgroundUser.setPassword( request.getPassword() );

        return backgroundUser;
    }

    @Override
    public IBackgroundUserDTO toIBackgroundUserDTO(BackgroundUser backgroundUser) {
        if ( backgroundUser == null ) {
            return null;
        }

        IBackgroundUserDTO iBackgroundUserDTO = new IBackgroundUserDTO();

        iBackgroundUserDTO.setId( backgroundUser.getId() );
        iBackgroundUserDTO.setLoginId( backgroundUser.getLoginId() );
        iBackgroundUserDTO.setStatus( backgroundUser.getStatus() );
        iBackgroundUserDTO.setAdministratorFlag( backgroundUser.getAdministratorFlag() );

        return iBackgroundUserDTO;
    }
}
