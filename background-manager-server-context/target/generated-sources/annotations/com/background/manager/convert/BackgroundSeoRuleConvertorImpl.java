package com.background.manager.convert;

import com.background.manager.model.BackgroundSeoRule;
import com.background.manager.model.dto.request.seo.ModifySeoRuleRequest;
import com.background.manager.model.dto.response.seo.BackgroundSeoRuleDTO;
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
public class BackgroundSeoRuleConvertorImpl implements BackgroundSeoRuleConvertor {

    @Override
    public BackgroundSeoRuleDTO toBackgroundSeoRuleDTO(BackgroundSeoRule backgroundSeoRule) {
        if ( backgroundSeoRule == null ) {
            return null;
        }

        BackgroundSeoRuleDTO backgroundSeoRuleDTO = new BackgroundSeoRuleDTO();

        backgroundSeoRuleDTO.setCreateTime( backgroundSeoRule.getCreateTime() );
        backgroundSeoRuleDTO.setCreator( backgroundSeoRule.getCreator() );
        backgroundSeoRuleDTO.setUpdateTime( backgroundSeoRule.getUpdateTime() );
        backgroundSeoRuleDTO.setModifier( backgroundSeoRule.getModifier() );
        backgroundSeoRuleDTO.setId( backgroundSeoRule.getId() );
        backgroundSeoRuleDTO.setSeoRule( backgroundSeoRule.getSeoRule() );

        return backgroundSeoRuleDTO;
    }

    @Override
    public List<BackgroundSeoRuleDTO> toBackgroundSeoRuleDTOList(List<BackgroundSeoRule> backgroundSeoRuleList) {
        if ( backgroundSeoRuleList == null ) {
            return null;
        }

        List<BackgroundSeoRuleDTO> list = new ArrayList<BackgroundSeoRuleDTO>( backgroundSeoRuleList.size() );
        for ( BackgroundSeoRule backgroundSeoRule : backgroundSeoRuleList ) {
            list.add( toBackgroundSeoRuleDTO( backgroundSeoRule ) );
        }

        return list;
    }

    @Override
    public BackgroundSeoRule toBackgroundSeoRule(ModifySeoRuleRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundSeoRule backgroundSeoRule = new BackgroundSeoRule();

        backgroundSeoRule.setId( request.getId() );
        backgroundSeoRule.setSeoRule( request.getSeoRule() );

        return backgroundSeoRule;
    }
}
