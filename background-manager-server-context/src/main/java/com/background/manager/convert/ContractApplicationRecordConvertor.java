package com.background.manager.convert;

import com.background.manager.model.ContractApplicationRecord;
import com.background.manager.model.dto.response.contract.ReChargeContractDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractApplicationRecordConvertor {

    ReChargeContractDTO toReChargeContractDTO(ContractApplicationRecord contractOperationNote);

    List<ReChargeContractDTO> toReChargeContractDTOList(List<ContractApplicationRecord> records);
}
