package com.background.manager.convert;

import com.background.manager.model.dto.request.workOrder.ReplyWorkOrderMessageRequest;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderMessageDTO;
import com.background.manager.model.work.BackgroundWorkOrderMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundWorkOrderMessageConvertor {


    BackgroundWorkOrderMessage toWorkOrderMessage(ReplyWorkOrderMessageRequest request);

    BackgroundWorkOrderMessageDTO toWorkOrderMessageDTO(BackgroundWorkOrderMessage backgroundWorkOrderMessage );

    List<BackgroundWorkOrderMessageDTO> toWorkOrderMessageDTOS(List<BackgroundWorkOrderMessage> backgroundWorkOrderMessageList);
}
