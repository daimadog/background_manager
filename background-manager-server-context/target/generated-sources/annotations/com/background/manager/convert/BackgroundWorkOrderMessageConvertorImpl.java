package com.background.manager.convert;

import com.background.manager.model.dto.request.workOrder.ReplyWorkOrderMessageRequest;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderMessageDTO;
import com.background.manager.model.work.BackgroundWorkOrderMessage;
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
public class BackgroundWorkOrderMessageConvertorImpl implements BackgroundWorkOrderMessageConvertor {

    @Override
    public BackgroundWorkOrderMessage toWorkOrderMessage(ReplyWorkOrderMessageRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundWorkOrderMessage backgroundWorkOrderMessage = new BackgroundWorkOrderMessage();

        backgroundWorkOrderMessage.setWorkOrderId( request.getWorkOrderId() );
        backgroundWorkOrderMessage.setMessageType( request.getMessageType() );
        backgroundWorkOrderMessage.setMessageText( request.getMessageText() );
        backgroundWorkOrderMessage.setMessageUrl( request.getMessageUrl() );

        return backgroundWorkOrderMessage;
    }

    @Override
    public BackgroundWorkOrderMessageDTO toWorkOrderMessageDTO(BackgroundWorkOrderMessage backgroundWorkOrderMessage) {
        if ( backgroundWorkOrderMessage == null ) {
            return null;
        }

        BackgroundWorkOrderMessageDTO backgroundWorkOrderMessageDTO = new BackgroundWorkOrderMessageDTO();

        backgroundWorkOrderMessageDTO.setId( backgroundWorkOrderMessage.getId() );
        backgroundWorkOrderMessageDTO.setDirection( backgroundWorkOrderMessage.getDirection() );
        backgroundWorkOrderMessageDTO.setMessageText( backgroundWorkOrderMessage.getMessageText() );
        backgroundWorkOrderMessageDTO.setCreateTime( backgroundWorkOrderMessage.getCreateTime() );

        return backgroundWorkOrderMessageDTO;
    }

    @Override
    public List<BackgroundWorkOrderMessageDTO> toWorkOrderMessageDTOS(List<BackgroundWorkOrderMessage> backgroundWorkOrderMessageList) {
        if ( backgroundWorkOrderMessageList == null ) {
            return null;
        }

        List<BackgroundWorkOrderMessageDTO> list = new ArrayList<BackgroundWorkOrderMessageDTO>( backgroundWorkOrderMessageList.size() );
        for ( BackgroundWorkOrderMessage backgroundWorkOrderMessage : backgroundWorkOrderMessageList ) {
            list.add( toWorkOrderMessageDTO( backgroundWorkOrderMessage ) );
        }

        return list;
    }
}
