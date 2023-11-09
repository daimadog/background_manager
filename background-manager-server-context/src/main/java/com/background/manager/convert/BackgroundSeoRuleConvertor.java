package com.background.manager.convert;

import com.background.manager.model.BackgroundSeoRule;
import com.background.manager.model.dto.request.seo.ModifySeoRuleRequest;
import com.background.manager.model.dto.response.seo.BackgroundSeoRuleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundSeoRuleConvertor {


    BackgroundSeoRuleDTO toBackgroundSeoRuleDTO(BackgroundSeoRule backgroundSeoRule);

    List<BackgroundSeoRuleDTO> toBackgroundSeoRuleDTOList(List<BackgroundSeoRule> backgroundSeoRuleList);

    BackgroundSeoRule toBackgroundSeoRule(ModifySeoRuleRequest request);
}
