package com.background.manager.convert;

import com.background.manager.model.dto.request.carousel.AddCarouselRequest;
import com.background.manager.model.dto.request.carousel.ModifyCarouselRequest;
import com.background.manager.model.dto.response.carousel.CarouselDTO;
import com.background.manager.model.BackgroundCarouselPage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundCarouselConvertor {


    CarouselDTO toCarouselDTO(BackgroundCarouselPage backgroundCarouselPage);

    List<CarouselDTO> toCarouselDTOS(List<BackgroundCarouselPage> backgroundCarouselPageList);

    BackgroundCarouselPage toBackgroundCarouselPage(AddCarouselRequest request);

    BackgroundCarouselPage toBackgroundCarouselPage(ModifyCarouselRequest request);
}
