package com.background.manager.convert;

import com.background.manager.model.BackgroundCmsActivity;
import com.background.manager.model.dto.request.activity.AddActivityRequest;
import com.background.manager.model.dto.request.activity.ModifyActivityRequest;
import com.background.manager.model.dto.response.activity.ActivityDTO;
import com.background.manager.model.dto.response.activity.ActivityDigestDTO;
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
public class BackgroundActivityConvertorImpl implements BackgroundActivityConvertor {

    @Override
    public ActivityDigestDTO toActivityDigestDTO(BackgroundCmsActivity backgroundCmsActivity) {
        if ( backgroundCmsActivity == null ) {
            return null;
        }

        ActivityDigestDTO activityDigestDTO = new ActivityDigestDTO();

        activityDigestDTO.setId( backgroundCmsActivity.getId() );
        activityDigestDTO.setTitle( backgroundCmsActivity.getTitle() );
        activityDigestDTO.setImgUrl( backgroundCmsActivity.getImgUrl() );
        activityDigestDTO.setIsTop( backgroundCmsActivity.getIsTop() );
        activityDigestDTO.setStatus( backgroundCmsActivity.getStatus() );
        activityDigestDTO.setColumnId( backgroundCmsActivity.getColumnId() );
        activityDigestDTO.setCreateTime( backgroundCmsActivity.getCreateTime() );
        activityDigestDTO.setCreator( backgroundCmsActivity.getCreator() );
        activityDigestDTO.setIsOriginal( backgroundCmsActivity.getIsOriginal() );
        activityDigestDTO.setContentUrl( backgroundCmsActivity.getContentUrl() );
        activityDigestDTO.setType( backgroundCmsActivity.getType() );

        return activityDigestDTO;
    }

    @Override
    public List<ActivityDigestDTO> toActivityDigestDTOS(List<BackgroundCmsActivity> backgroundCmsActivityList) {
        if ( backgroundCmsActivityList == null ) {
            return null;
        }

        List<ActivityDigestDTO> list = new ArrayList<ActivityDigestDTO>( backgroundCmsActivityList.size() );
        for ( BackgroundCmsActivity backgroundCmsActivity : backgroundCmsActivityList ) {
            list.add( toActivityDigestDTO( backgroundCmsActivity ) );
        }

        return list;
    }

    @Override
    public BackgroundCmsActivity toBackgroundActivity(AddActivityRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundCmsActivity backgroundCmsActivity = new BackgroundCmsActivity();

        backgroundCmsActivity.setTitle( request.getTitle() );
        backgroundCmsActivity.setImgUrl( request.getImgUrl() );
        backgroundCmsActivity.setDescription( request.getDescription() );
        backgroundCmsActivity.setColumnId( request.getColumnId() );
        backgroundCmsActivity.setStatus( request.getStatus() );
        backgroundCmsActivity.setIsOriginal( request.getIsOriginal() );
        backgroundCmsActivity.setContentUrl( request.getContentUrl() );
        backgroundCmsActivity.setIsTop( request.getIsTop() );
        backgroundCmsActivity.setType( request.getType() );
        backgroundCmsActivity.setContent( request.getContent() );

        return backgroundCmsActivity;
    }

    @Override
    public BackgroundCmsActivity toBackgroundActivity(ModifyActivityRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundCmsActivity backgroundCmsActivity = new BackgroundCmsActivity();

        backgroundCmsActivity.setId( request.getId() );
        backgroundCmsActivity.setTitle( request.getTitle() );
        backgroundCmsActivity.setImgUrl( request.getImgUrl() );
        backgroundCmsActivity.setDescription( request.getDescription() );
        backgroundCmsActivity.setColumnId( request.getColumnId() );
        backgroundCmsActivity.setStatus( request.getStatus() );
        backgroundCmsActivity.setIsOriginal( request.getIsOriginal() );
        backgroundCmsActivity.setContentUrl( request.getContentUrl() );
        backgroundCmsActivity.setIsTop( request.getIsTop() );
        backgroundCmsActivity.setType( request.getType() );
        backgroundCmsActivity.setContent( request.getContent() );

        return backgroundCmsActivity;
    }

    @Override
    public ActivityDTO toActivityDTO(BackgroundCmsActivity backgroundCmsActivity) {
        if ( backgroundCmsActivity == null ) {
            return null;
        }

        ActivityDTO activityDTO = new ActivityDTO();

        activityDTO.setTitle( backgroundCmsActivity.getTitle() );
        activityDTO.setImgUrl( backgroundCmsActivity.getImgUrl() );
        activityDTO.setDescription( backgroundCmsActivity.getDescription() );
        activityDTO.setColumnId( backgroundCmsActivity.getColumnId() );
        activityDTO.setStatus( backgroundCmsActivity.getStatus() );
        activityDTO.setContent( backgroundCmsActivity.getContent() );
        activityDTO.setIsOriginal( backgroundCmsActivity.getIsOriginal() );
        activityDTO.setContentUrl( backgroundCmsActivity.getContentUrl() );
        activityDTO.setType( backgroundCmsActivity.getType() );
        activityDTO.setIsTop( backgroundCmsActivity.getIsTop() );

        return activityDTO;
    }
}
