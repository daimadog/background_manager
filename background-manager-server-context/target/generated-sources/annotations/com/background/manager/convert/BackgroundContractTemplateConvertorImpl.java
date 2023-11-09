package com.background.manager.convert;

import com.background.manager.model.BackgroundContractTemplate;
import com.background.manager.model.dto.request.template.CreateTemplateRequest;
import com.background.manager.model.dto.request.template.ModifyContractTemplateRequest;
import com.background.manager.model.dto.response.template.ContractTemplateDTO;
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
public class BackgroundContractTemplateConvertorImpl implements BackgroundContractTemplateConvertor {

    @Override
    public BackgroundContractTemplate toBackgroundContractTemplate(CreateTemplateRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundContractTemplate backgroundContractTemplate = new BackgroundContractTemplate();

        backgroundContractTemplate.setName( request.getName() );
        backgroundContractTemplate.setDescription( request.getDescription() );
        backgroundContractTemplate.setTemplateUrl( request.getTemplateUrl() );

        return backgroundContractTemplate;
    }

    @Override
    public ContractTemplateDTO toContractTemplateDTO(BackgroundContractTemplate backgroundContractTemplate) {
        if ( backgroundContractTemplate == null ) {
            return null;
        }

        ContractTemplateDTO contractTemplateDTO = new ContractTemplateDTO();

        contractTemplateDTO.setId( backgroundContractTemplate.getId() );
        contractTemplateDTO.setName( backgroundContractTemplate.getName() );
        contractTemplateDTO.setDescription( backgroundContractTemplate.getDescription() );
        contractTemplateDTO.setStatus( backgroundContractTemplate.getStatus() );
        contractTemplateDTO.setTemplateUrl( backgroundContractTemplate.getTemplateUrl() );
        contractTemplateDTO.setCreateTime( backgroundContractTemplate.getCreateTime() );

        return contractTemplateDTO;
    }

    @Override
    public List<ContractTemplateDTO> toContractTemplateDTOS(List<BackgroundContractTemplate> backgroundContractTemplates) {
        if ( backgroundContractTemplates == null ) {
            return null;
        }

        List<ContractTemplateDTO> list = new ArrayList<ContractTemplateDTO>( backgroundContractTemplates.size() );
        for ( BackgroundContractTemplate backgroundContractTemplate : backgroundContractTemplates ) {
            list.add( toContractTemplateDTO( backgroundContractTemplate ) );
        }

        return list;
    }

    @Override
    public BackgroundContractTemplate toBackgroundContractTemplate(ModifyContractTemplateRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundContractTemplate backgroundContractTemplate = new BackgroundContractTemplate();

        backgroundContractTemplate.setId( request.getId() );
        backgroundContractTemplate.setName( request.getName() );
        backgroundContractTemplate.setDescription( request.getDescription() );
        backgroundContractTemplate.setTemplateUrl( request.getTemplateUrl() );

        return backgroundContractTemplate;
    }
}
