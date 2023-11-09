package com.background.manager.convert;

import com.background.manager.dto.response.user.PasswordRuleDTO;
import com.background.manager.model.PasswordRule;
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
public class PasswordRuleConvertorImpl implements PasswordRuleConvertor {

    @Override
    public PasswordRuleDTO toPasswordRuleDTO(PasswordRule passwordRule) {
        if ( passwordRule == null ) {
            return null;
        }

        PasswordRuleDTO passwordRuleDTO = new PasswordRuleDTO();

        passwordRuleDTO.setId( passwordRule.getId() );
        passwordRuleDTO.setType( passwordRule.getType() );
        passwordRuleDTO.setDescription( passwordRule.getDescription() );

        return passwordRuleDTO;
    }

    @Override
    public List<PasswordRuleDTO> toPasswordRuleDTOS(List<PasswordRule> passwordRuleList) {
        if ( passwordRuleList == null ) {
            return null;
        }

        List<PasswordRuleDTO> list = new ArrayList<PasswordRuleDTO>( passwordRuleList.size() );
        for ( PasswordRule passwordRule : passwordRuleList ) {
            list.add( toPasswordRuleDTO( passwordRule ) );
        }

        return list;
    }
}
