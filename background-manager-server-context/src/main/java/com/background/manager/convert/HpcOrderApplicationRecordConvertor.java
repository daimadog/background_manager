package com.background.manager.convert;

import com.background.manager.model.HpcOrderApplicationRecord;
import com.background.manager.model.dto.response.order.HpcOrderApplicationRecordDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HpcOrderApplicationRecordConvertor {

    HpcOrderApplicationRecordDTO toHpcOrderApplicationRecordDTO(HpcOrderApplicationRecord record);

}
