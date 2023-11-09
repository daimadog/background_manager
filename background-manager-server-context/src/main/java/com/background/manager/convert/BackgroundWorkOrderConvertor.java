package com.background.manager.convert;

import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDTO;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDigestDTO;
import com.background.manager.model.work.BackgroundWorkOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundWorkOrderConvertor {

    BackgroundWorkOrderDigestDTO toBackgroundWorkOrderDigestDTO (BackgroundWorkOrder backgroundWorkOrder);

    List<BackgroundWorkOrderDigestDTO> toBackgroundWorkOrderDigestDTOS(List<BackgroundWorkOrder> records);

    BackgroundWorkOrderDTO toBackgroundWorkOrderDTO(BackgroundWorkOrder backgroundWorkOrder);
}
