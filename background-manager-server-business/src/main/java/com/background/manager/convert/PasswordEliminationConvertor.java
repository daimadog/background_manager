package com.background.manager.convert;

import com.background.manager.dto.response.user.PasswordEliminationDTO;
import com.background.manager.model.PasswordElimination;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PasswordEliminationConvertor {

    PasswordEliminationDTO toPasswordEliminationDTO(PasswordElimination passwordElimination);

    List<PasswordEliminationDTO> toPasswordEliminationDTOS(List<PasswordElimination> passwordEliminations);
}
