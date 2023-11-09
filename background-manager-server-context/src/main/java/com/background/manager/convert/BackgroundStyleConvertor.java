package com.background.manager.convert;

import com.background.manager.model.BackgroundStyle;
import com.background.manager.model.dto.response.style.BackgroundStyleDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BackgroundStyleConvertor {

    BackgroundStyleDTO toBackgroundStyleDTO(BackgroundStyle backgroundStyle);

    List<BackgroundStyleDTO> toBackgroundStyleDTOS(List<BackgroundStyle> backgroundStyleList);
}
