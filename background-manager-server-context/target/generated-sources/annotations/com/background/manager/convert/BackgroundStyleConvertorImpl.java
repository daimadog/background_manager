package com.background.manager.convert;

import com.background.manager.model.BackgroundStyle;
import com.background.manager.model.dto.response.style.BackgroundStyleDTO;
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
public class BackgroundStyleConvertorImpl implements BackgroundStyleConvertor {

    @Override
    public BackgroundStyleDTO toBackgroundStyleDTO(BackgroundStyle backgroundStyle) {
        if ( backgroundStyle == null ) {
            return null;
        }

        BackgroundStyleDTO backgroundStyleDTO = new BackgroundStyleDTO();

        backgroundStyleDTO.setId( backgroundStyle.getId() );
        backgroundStyleDTO.setName( backgroundStyle.getName() );
        backgroundStyleDTO.setIdentify( backgroundStyle.getIdentify() );
        backgroundStyleDTO.setColor( backgroundStyle.getColor() );

        return backgroundStyleDTO;
    }

    @Override
    public List<BackgroundStyleDTO> toBackgroundStyleDTOS(List<BackgroundStyle> backgroundStyleList) {
        if ( backgroundStyleList == null ) {
            return null;
        }

        List<BackgroundStyleDTO> list = new ArrayList<BackgroundStyleDTO>( backgroundStyleList.size() );
        for ( BackgroundStyle backgroundStyle : backgroundStyleList ) {
            list.add( toBackgroundStyleDTO( backgroundStyle ) );
        }

        return list;
    }
}
