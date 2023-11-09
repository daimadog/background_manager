package com.background.manager.convert;

import com.background.manager.model.BackgroundContract;
import com.background.manager.model.dto.request.contract.CreateContractRequest;
import com.background.manager.model.dto.request.contract.ModifyContractRequest;
import com.background.manager.model.dto.response.contract.BackgroundContractDTO;
import com.background.manager.model.dto.response.contract.BackgroundContractDigestDTO;
import com.background.manager.model.dto.response.receipt.BackgroundContractUserDigestDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundContractConvertor {

    BackgroundContract toBackgroundContract(CreateContractRequest request);

    BackgroundContractDigestDTO toBackgroundContractDigestDTO(BackgroundContract backgroundContract);

    BackgroundContract toBackgroundContract(ModifyContractRequest request);

    BackgroundContractDTO toBackgroundContactDTO(BackgroundContract backgroundContract);

    BackgroundContractUserDigestDTO toBackgroundContractUserDigestDTO(BackgroundContract records);

    List<BackgroundContractUserDigestDTO> toBackgroundContractUserDigestDTOS(List<BackgroundContract> records);
}
