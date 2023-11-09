package com.background.manager.convert;

import com.background.manager.model.BackgroundCarouselPage;
import com.background.manager.model.dto.request.carousel.AddCarouselRequest;
import com.background.manager.model.dto.request.carousel.ModifyCarouselRequest;
import com.background.manager.model.dto.response.carousel.CarouselDTO;
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
public class BackgroundCarouselConvertorImpl implements BackgroundCarouselConvertor {

    @Override
    public CarouselDTO toCarouselDTO(BackgroundCarouselPage backgroundCarouselPage) {
        if ( backgroundCarouselPage == null ) {
            return null;
        }

        CarouselDTO carouselDTO = new CarouselDTO();

        carouselDTO.setId( backgroundCarouselPage.getId() );
        carouselDTO.setTitle( backgroundCarouselPage.getTitle() );
        carouselDTO.setContent( backgroundCarouselPage.getContent() );
        carouselDTO.setImageUrl( backgroundCarouselPage.getImageUrl() );
        carouselDTO.setExternalUrl( backgroundCarouselPage.getExternalUrl() );

        return carouselDTO;
    }

    @Override
    public List<CarouselDTO> toCarouselDTOS(List<BackgroundCarouselPage> backgroundCarouselPageList) {
        if ( backgroundCarouselPageList == null ) {
            return null;
        }

        List<CarouselDTO> list = new ArrayList<CarouselDTO>( backgroundCarouselPageList.size() );
        for ( BackgroundCarouselPage backgroundCarouselPage : backgroundCarouselPageList ) {
            list.add( toCarouselDTO( backgroundCarouselPage ) );
        }

        return list;
    }

    @Override
    public BackgroundCarouselPage toBackgroundCarouselPage(AddCarouselRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundCarouselPage backgroundCarouselPage = new BackgroundCarouselPage();

        backgroundCarouselPage.setTitle( request.getTitle() );
        backgroundCarouselPage.setContent( request.getContent() );
        backgroundCarouselPage.setImageUrl( request.getImageUrl() );
        backgroundCarouselPage.setExternalUrl( request.getExternalUrl() );

        return backgroundCarouselPage;
    }

    @Override
    public BackgroundCarouselPage toBackgroundCarouselPage(ModifyCarouselRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundCarouselPage backgroundCarouselPage = new BackgroundCarouselPage();

        backgroundCarouselPage.setId( request.getId() );
        backgroundCarouselPage.setTitle( request.getTitle() );
        backgroundCarouselPage.setContent( request.getContent() );
        backgroundCarouselPage.setImageUrl( request.getImageUrl() );
        backgroundCarouselPage.setExternalUrl( request.getExternalUrl() );

        return backgroundCarouselPage;
    }
}
