package com.background.manager.convert;

import com.background.manager.model.BackgroundAnnouncement;
import com.background.manager.model.dto.request.announcement.AddAnnouncementRequest;
import com.background.manager.model.dto.response.announcement.BackgroundAnnouncementDigestDTO;
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
public class BackgroundAnnouncementConvertorImpl implements BackgroundAnnouncementConvertor {

    @Override
    public BackgroundAnnouncement toBackgroundAnnouncement(AddAnnouncementRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundAnnouncement backgroundAnnouncement = new BackgroundAnnouncement();

        backgroundAnnouncement.setType( request.getType() );
        backgroundAnnouncement.setTitle( request.getTitle() );
        backgroundAnnouncement.setContent( request.getContent() );
        backgroundAnnouncement.setAnnouncementTime( request.getAnnouncementTime() );

        return backgroundAnnouncement;
    }

    @Override
    public BackgroundAnnouncementDigestDTO toBackgroundAnnouncementDigestDTO(BackgroundAnnouncement backgroundAnnouncement) {
        if ( backgroundAnnouncement == null ) {
            return null;
        }

        BackgroundAnnouncementDigestDTO backgroundAnnouncementDigestDTO = new BackgroundAnnouncementDigestDTO();

        backgroundAnnouncementDigestDTO.setId( backgroundAnnouncement.getId() );
        backgroundAnnouncementDigestDTO.setType( backgroundAnnouncement.getType() );
        backgroundAnnouncementDigestDTO.setApplyStatus( backgroundAnnouncement.getApplyStatus() );
        backgroundAnnouncementDigestDTO.setTitle( backgroundAnnouncement.getTitle() );
        backgroundAnnouncementDigestDTO.setContent( backgroundAnnouncement.getContent() );
        backgroundAnnouncementDigestDTO.setAnnouncementTime( backgroundAnnouncement.getAnnouncementTime() );

        return backgroundAnnouncementDigestDTO;
    }

    @Override
    public List<BackgroundAnnouncementDigestDTO> toBackgroundAnnouncementDigestDTOs(List<BackgroundAnnouncement> records) {
        if ( records == null ) {
            return null;
        }

        List<BackgroundAnnouncementDigestDTO> list = new ArrayList<BackgroundAnnouncementDigestDTO>( records.size() );
        for ( BackgroundAnnouncement backgroundAnnouncement : records ) {
            list.add( toBackgroundAnnouncementDigestDTO( backgroundAnnouncement ) );
        }

        return list;
    }
}
