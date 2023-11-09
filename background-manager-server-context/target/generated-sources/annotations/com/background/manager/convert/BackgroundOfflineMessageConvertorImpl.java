package com.background.manager.convert;

import com.background.manager.model.BackgroundOfflineMessage;
import com.background.manager.model.dto.request.offlineRegistration.AddRegistrationRequest;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDTO;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDigestDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class BackgroundOfflineMessageConvertorImpl implements BackgroundOfflineMessageConvertor {

    @Override
    public BackgroundOfflineMessage toBackgroundOfflineMessage(AddRegistrationRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundOfflineMessage backgroundOfflineMessage = new BackgroundOfflineMessage();

        backgroundOfflineMessage.setName( request.getName() );
        backgroundOfflineMessage.setCompany( request.getCompany() );
        backgroundOfflineMessage.setStatus( request.getStatus() );
        backgroundOfflineMessage.setPhone( request.getPhone() );
        backgroundOfflineMessage.setEmail( request.getEmail() );
        backgroundOfflineMessage.setMessageContent( request.getMessageContent() );

        return backgroundOfflineMessage;
    }

    @Override
    public OfflineMessageDigestDTO toOfflineMessageDTO(BackgroundOfflineMessage backgroundOfflineMessage) {
        if ( backgroundOfflineMessage == null ) {
            return null;
        }

        OfflineMessageDigestDTO offlineMessageDigestDTO = new OfflineMessageDigestDTO();

        offlineMessageDigestDTO.setId( backgroundOfflineMessage.getId() );
        offlineMessageDigestDTO.setName( backgroundOfflineMessage.getName() );
        offlineMessageDigestDTO.setCompany( backgroundOfflineMessage.getCompany() );
        offlineMessageDigestDTO.setStatus( backgroundOfflineMessage.getStatus() );
        offlineMessageDigestDTO.setPhone( backgroundOfflineMessage.getPhone() );
        offlineMessageDigestDTO.setEmail( backgroundOfflineMessage.getEmail() );
        offlineMessageDigestDTO.setProcessState( backgroundOfflineMessage.getProcessState() );
        offlineMessageDigestDTO.setMessageContent( backgroundOfflineMessage.getMessageContent() );

        return offlineMessageDigestDTO;
    }

    @Override
    public List<OfflineMessageDigestDTO> toOfflineMessageDTOS(List<BackgroundOfflineMessage> records) {
        if ( records == null ) {
            return null;
        }

        List<OfflineMessageDigestDTO> list = new ArrayList<OfflineMessageDigestDTO>( records.size() );
        for ( BackgroundOfflineMessage backgroundOfflineMessage : records ) {
            list.add( toOfflineMessageDTO( backgroundOfflineMessage ) );
        }

        return list;
    }

    @Override
    public OfflineMessageDTO toBackgroundOfflineMessage(BackgroundOfflineMessage backgroundOfflineMessage) {
        if ( backgroundOfflineMessage == null ) {
            return null;
        }

        OfflineMessageDTO offlineMessageDTO = new OfflineMessageDTO();

        offlineMessageDTO.setName( backgroundOfflineMessage.getName() );
        offlineMessageDTO.setCompany( backgroundOfflineMessage.getCompany() );
        offlineMessageDTO.setStatus( backgroundOfflineMessage.getStatus() );
        offlineMessageDTO.setPhone( backgroundOfflineMessage.getPhone() );
        offlineMessageDTO.setEmail( backgroundOfflineMessage.getEmail() );
        offlineMessageDTO.setProcessState( backgroundOfflineMessage.getProcessState() );
        offlineMessageDTO.setMessageContent( backgroundOfflineMessage.getMessageContent() );
        offlineMessageDTO.setCreateTime( backgroundOfflineMessage.getCreateTime() );

        return offlineMessageDTO;
    }
}
