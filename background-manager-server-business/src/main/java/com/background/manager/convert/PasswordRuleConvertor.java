package com.background.manager.convert;

import com.background.manager.dto.response.user.PasswordRuleDTO;
import com.background.manager.model.PasswordRule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PasswordRuleConvertor {

    PasswordRuleDTO toPasswordRuleDTO(PasswordRule passwordRule);

    List<PasswordRuleDTO> toPasswordRuleDTOS(List<PasswordRule> passwordRuleList);
}
