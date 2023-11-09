package com.background.manager.convert;

import com.background.manager.model.BackgroundInfrastructure;
import com.background.manager.model.dto.request.style.ModifyInfrastructureRequest;
import com.background.manager.model.dto.response.style.BackgroundInfrastructureDTO;
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
public class BackgroundInfrastructureConvertorImpl implements BackgroundInfrastructureConvertor {

    @Override
    public BackgroundInfrastructureDTO toBackgroundInfrastructureDTO(BackgroundInfrastructure backgroundInfrastructure) {
        if ( backgroundInfrastructure == null ) {
            return null;
        }

        BackgroundInfrastructureDTO backgroundInfrastructureDTO = new BackgroundInfrastructureDTO();

        backgroundInfrastructureDTO.setId( backgroundInfrastructure.getId() );
        backgroundInfrastructureDTO.setName( backgroundInfrastructure.getName() );
        backgroundInfrastructureDTO.setNumber( backgroundInfrastructure.getNumber() );
        backgroundInfrastructureDTO.setCreateTime( backgroundInfrastructure.getCreateTime() );
        backgroundInfrastructureDTO.setUpdateTime( backgroundInfrastructure.getUpdateTime() );

        return backgroundInfrastructureDTO;
    }

    @Override
    public List<BackgroundInfrastructureDTO> toBackgroundInfrastructureDTOS(List<BackgroundInfrastructure> backgroundInfrastructureList) {
        if ( backgroundInfrastructureList == null ) {
            return null;
        }

        List<BackgroundInfrastructureDTO> list = new ArrayList<BackgroundInfrastructureDTO>( backgroundInfrastructureList.size() );
        for ( BackgroundInfrastructure backgroundInfrastructure : backgroundInfrastructureList ) {
            list.add( toBackgroundInfrastructureDTO( backgroundInfrastructure ) );
        }

        return list;
    }

    @Override
    public BackgroundInfrastructure toBackgroundInfrastructure(ModifyInfrastructureRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundInfrastructure backgroundInfrastructure = new BackgroundInfrastructure();

        backgroundInfrastructure.setId( request.getId() );
        backgroundInfrastructure.setName( request.getName() );
        backgroundInfrastructure.setNumber( request.getNumber() );

        return backgroundInfrastructure;
    }
}
