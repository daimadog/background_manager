package com.background.manager.convert;

import com.background.manager.model.BackgroundInfrastructure;
import com.background.manager.model.dto.request.style.ModifyInfrastructureRequest;
import com.background.manager.model.dto.response.style.BackgroundInfrastructureDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundInfrastructureConvertor {

    BackgroundInfrastructureDTO toBackgroundInfrastructureDTO(BackgroundInfrastructure backgroundInfrastructure);

    List<BackgroundInfrastructureDTO> toBackgroundInfrastructureDTOS(List<BackgroundInfrastructure> backgroundInfrastructureList);

    BackgroundInfrastructure toBackgroundInfrastructure(ModifyInfrastructureRequest request);
}
