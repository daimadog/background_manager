package com.background.manager.convert;

import com.background.manager.model.dto.request.home.AddPartnersRequest;
import com.background.manager.model.dto.request.home.AddTypicalClassRequest;
import com.background.manager.model.dto.request.home.ModifyPartnersRequest;
import com.background.manager.model.dto.request.home.ModifyTypicalClassRequest;
import com.background.manager.model.dto.response.home.PartnersDTO;
import com.background.manager.model.dto.response.home.TypicalClassDTO;
import com.background.manager.model.dto.response.home.TypicalClassDigestDTO;
import com.background.manager.model.BackgroundPartners;
import com.background.manager.model.BackgroundTypicalClass;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface HomeConvertor {

    BackgroundTypicalClass toBackgroundTypicalClass(AddTypicalClassRequest request);

    TypicalClassDigestDTO toBackgroundTypicalClassDigestDTO(BackgroundTypicalClass backgroundTypicalClass);

    List<TypicalClassDigestDTO> toBackgroundTypicalClassDigestDTOS(List<BackgroundTypicalClass> records);

    TypicalClassDTO toBackgroundTypicalClassDTO(BackgroundTypicalClass backgroundTypicalClass);

    BackgroundTypicalClass toBackgroundTypicalClass(ModifyTypicalClassRequest request);

    BackgroundPartners toBackgroundPartners(AddPartnersRequest request);

    PartnersDTO toBackgroundPartnersDTO(BackgroundPartners backgroundPartnersList );

    List<PartnersDTO> toBackgroundPartnersDTOS(List<BackgroundPartners> backgroundPartnersList);

    BackgroundPartners toBackgroundPartners(ModifyPartnersRequest request);
}
