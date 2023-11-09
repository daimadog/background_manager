package com.background.manager.convert;

import com.background.manager.model.BackgroundPartners;
import com.background.manager.model.BackgroundTypicalClass;
import com.background.manager.model.dto.request.home.AddPartnersRequest;
import com.background.manager.model.dto.request.home.AddTypicalClassRequest;
import com.background.manager.model.dto.request.home.ModifyPartnersRequest;
import com.background.manager.model.dto.request.home.ModifyTypicalClassRequest;
import com.background.manager.model.dto.response.home.PartnersDTO;
import com.background.manager.model.dto.response.home.TypicalClassDTO;
import com.background.manager.model.dto.response.home.TypicalClassDigestDTO;
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
public class HomeConvertorImpl implements HomeConvertor {

    @Override
    public BackgroundTypicalClass toBackgroundTypicalClass(AddTypicalClassRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundTypicalClass backgroundTypicalClass = new BackgroundTypicalClass();

        backgroundTypicalClass.setTitle( request.getTitle() );
        backgroundTypicalClass.setSubtitle( request.getSubtitle() );
        backgroundTypicalClass.setImageUrl( request.getImageUrl() );
        backgroundTypicalClass.setContext( request.getContext() );
        backgroundTypicalClass.setConnectUrl( request.getConnectUrl() );

        return backgroundTypicalClass;
    }

    @Override
    public TypicalClassDigestDTO toBackgroundTypicalClassDigestDTO(BackgroundTypicalClass backgroundTypicalClass) {
        if ( backgroundTypicalClass == null ) {
            return null;
        }

        TypicalClassDigestDTO typicalClassDigestDTO = new TypicalClassDigestDTO();

        typicalClassDigestDTO.setId( backgroundTypicalClass.getId() );
        typicalClassDigestDTO.setTitle( backgroundTypicalClass.getTitle() );

        return typicalClassDigestDTO;
    }

    @Override
    public List<TypicalClassDigestDTO> toBackgroundTypicalClassDigestDTOS(List<BackgroundTypicalClass> records) {
        if ( records == null ) {
            return null;
        }

        List<TypicalClassDigestDTO> list = new ArrayList<TypicalClassDigestDTO>( records.size() );
        for ( BackgroundTypicalClass backgroundTypicalClass : records ) {
            list.add( toBackgroundTypicalClassDigestDTO( backgroundTypicalClass ) );
        }

        return list;
    }

    @Override
    public TypicalClassDTO toBackgroundTypicalClassDTO(BackgroundTypicalClass backgroundTypicalClass) {
        if ( backgroundTypicalClass == null ) {
            return null;
        }

        TypicalClassDTO typicalClassDTO = new TypicalClassDTO();

        typicalClassDTO.setId( backgroundTypicalClass.getId() );
        typicalClassDTO.setTitle( backgroundTypicalClass.getTitle() );
        typicalClassDTO.setSubtitle( backgroundTypicalClass.getSubtitle() );
        typicalClassDTO.setImageUrl( backgroundTypicalClass.getImageUrl() );
        typicalClassDTO.setContext( backgroundTypicalClass.getContext() );
        typicalClassDTO.setConnectUrl( backgroundTypicalClass.getConnectUrl() );

        return typicalClassDTO;
    }

    @Override
    public BackgroundTypicalClass toBackgroundTypicalClass(ModifyTypicalClassRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundTypicalClass backgroundTypicalClass = new BackgroundTypicalClass();

        backgroundTypicalClass.setId( request.getId() );
        backgroundTypicalClass.setTitle( request.getTitle() );
        backgroundTypicalClass.setSubtitle( request.getSubtitle() );
        backgroundTypicalClass.setImageUrl( request.getImageUrl() );
        backgroundTypicalClass.setContext( request.getContext() );
        backgroundTypicalClass.setConnectUrl( request.getConnectUrl() );

        return backgroundTypicalClass;
    }

    @Override
    public BackgroundPartners toBackgroundPartners(AddPartnersRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundPartners backgroundPartners = new BackgroundPartners();

        backgroundPartners.setTitle( request.getTitle() );
        backgroundPartners.setImageUrl( request.getImageUrl() );

        return backgroundPartners;
    }

    @Override
    public PartnersDTO toBackgroundPartnersDTO(BackgroundPartners backgroundPartnersList) {
        if ( backgroundPartnersList == null ) {
            return null;
        }

        PartnersDTO partnersDTO = new PartnersDTO();

        partnersDTO.setImageUrl( backgroundPartnersList.getImageUrl() );

        return partnersDTO;
    }

    @Override
    public List<PartnersDTO> toBackgroundPartnersDTOS(List<BackgroundPartners> backgroundPartnersList) {
        if ( backgroundPartnersList == null ) {
            return null;
        }

        List<PartnersDTO> list = new ArrayList<PartnersDTO>( backgroundPartnersList.size() );
        for ( BackgroundPartners backgroundPartners : backgroundPartnersList ) {
            list.add( toBackgroundPartnersDTO( backgroundPartners ) );
        }

        return list;
    }

    @Override
    public BackgroundPartners toBackgroundPartners(ModifyPartnersRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundPartners backgroundPartners = new BackgroundPartners();

        backgroundPartners.setId( request.getId() );
        backgroundPartners.setTitle( request.getTitle() );
        backgroundPartners.setImageUrl( request.getImageUrl() );

        return backgroundPartners;
    }
}
