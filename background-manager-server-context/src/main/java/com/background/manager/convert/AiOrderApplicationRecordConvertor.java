package com.background.manager.convert;

import com.background.manager.model.AiOrderApplicationRecord;
import com.background.manager.model.dto.response.order.AiOrderApplicationRecordDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AiOrderApplicationRecordConvertor {

    AiOrderApplicationRecordDTO toAiOrderApplicationRecordDTO(AiOrderApplicationRecord aiOrderApplicationRecord);
}
