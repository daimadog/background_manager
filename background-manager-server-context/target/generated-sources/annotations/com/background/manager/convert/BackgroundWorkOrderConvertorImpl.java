package com.background.manager.convert;

import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDTO;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDigestDTO;
import com.background.manager.model.work.BackgroundWorkOrder;
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
public class BackgroundWorkOrderConvertorImpl implements BackgroundWorkOrderConvertor {

    @Override
    public BackgroundWorkOrderDigestDTO toBackgroundWorkOrderDigestDTO(BackgroundWorkOrder backgroundWorkOrder) {
        if ( backgroundWorkOrder == null ) {
            return null;
        }

        BackgroundWorkOrderDigestDTO backgroundWorkOrderDigestDTO = new BackgroundWorkOrderDigestDTO();

        backgroundWorkOrderDigestDTO.setId( backgroundWorkOrder.getId() );
        backgroundWorkOrderDigestDTO.setTitle( backgroundWorkOrder.getTitle() );
        backgroundWorkOrderDigestDTO.setContent( backgroundWorkOrder.getContent() );
        backgroundWorkOrderDigestDTO.setType( backgroundWorkOrder.getType() );
        backgroundWorkOrderDigestDTO.setStatus( backgroundWorkOrder.getStatus() );
        backgroundWorkOrderDigestDTO.setSubmitTime( backgroundWorkOrder.getSubmitTime() );

        return backgroundWorkOrderDigestDTO;
    }

    @Override
    public List<BackgroundWorkOrderDigestDTO> toBackgroundWorkOrderDigestDTOS(List<BackgroundWorkOrder> records) {
        if ( records == null ) {
            return null;
        }

        List<BackgroundWorkOrderDigestDTO> list = new ArrayList<BackgroundWorkOrderDigestDTO>( records.size() );
        for ( BackgroundWorkOrder backgroundWorkOrder : records ) {
            list.add( toBackgroundWorkOrderDigestDTO( backgroundWorkOrder ) );
        }

        return list;
    }

    @Override
    public BackgroundWorkOrderDTO toBackgroundWorkOrderDTO(BackgroundWorkOrder backgroundWorkOrder) {
        if ( backgroundWorkOrder == null ) {
            return null;
        }

        BackgroundWorkOrderDTO backgroundWorkOrderDTO = new BackgroundWorkOrderDTO();

        backgroundWorkOrderDTO.setId( backgroundWorkOrder.getId() );
        backgroundWorkOrderDTO.setTitle( backgroundWorkOrder.getTitle() );
        backgroundWorkOrderDTO.setType( backgroundWorkOrder.getType() );
        backgroundWorkOrderDTO.setStatus( backgroundWorkOrder.getStatus() );
        backgroundWorkOrderDTO.setSubmitTime( backgroundWorkOrder.getSubmitTime() );
        backgroundWorkOrderDTO.setHandleBy( backgroundWorkOrder.getHandleBy() );
        backgroundWorkOrderDTO.setContent( backgroundWorkOrder.getContent() );

        return backgroundWorkOrderDTO;
    }
}
