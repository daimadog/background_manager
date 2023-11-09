package com.background.manager.convert;

import com.background.manager.model.dto.request.activity.AddActivityRequest;
import com.background.manager.model.dto.request.activity.ModifyActivityRequest;
import com.background.manager.model.dto.response.activity.ActivityDTO;
import com.background.manager.model.dto.response.activity.ActivityDigestDTO;
import com.background.manager.model.BackgroundCmsActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @Description: 活动管理转换器
 * @Author: 杜黎明
 * @Date: 2022/10/26 20:30:38
 * @Version: 1.0.0
 */
@Mapper(componentModel="spring")
public interface BackgroundActivityConvertor {

    ActivityDigestDTO toActivityDigestDTO ( BackgroundCmsActivity backgroundCmsActivity);

    List<ActivityDigestDTO> toActivityDigestDTOS(List<BackgroundCmsActivity> backgroundCmsActivityList);

    BackgroundCmsActivity toBackgroundActivity(AddActivityRequest request);

    BackgroundCmsActivity toBackgroundActivity(ModifyActivityRequest request);

    @Mappings(
            @Mapping(target = "context",ignore = true)
    )
    ActivityDTO toActivityDTO(BackgroundCmsActivity backgroundCmsActivity);
}
