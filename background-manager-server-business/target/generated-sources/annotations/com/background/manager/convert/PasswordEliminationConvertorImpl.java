package com.background.manager.convert;

import com.background.manager.dto.response.user.PasswordEliminationDTO;
import com.background.manager.model.PasswordElimination;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:24+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class PasswordEliminationConvertorImpl implements PasswordEliminationConvertor {

    @Override
    public PasswordEliminationDTO toPasswordEliminationDTO(PasswordElimination passwordElimination) {
        if ( passwordElimination == null ) {
            return null;
        }

        PasswordEliminationDTO passwordEliminationDTO = new PasswordEliminationDTO();

        passwordEliminationDTO.setId( passwordElimination.getId() );
        passwordEliminationDTO.setPassword( passwordElimination.getPassword() );

        return passwordEliminationDTO;
    }

    @Override
    public List<PasswordEliminationDTO> toPasswordEliminationDTOS(List<PasswordElimination> passwordEliminations) {
        if ( passwordEliminations == null ) {
            return null;
        }

        List<PasswordEliminationDTO> list = new ArrayList<PasswordEliminationDTO>( passwordEliminations.size() );
        for ( PasswordElimination passwordElimination : passwordEliminations ) {
            list.add( toPasswordEliminationDTO( passwordElimination ) );
        }

        return list;
    }
}
