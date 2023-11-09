package com.background.manager.service.impl;

import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.VideoActivityMapper;
import com.background.manager.model.activity.ActivityVideo;
import com.background.manager.service.VideoActivityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class VideoActivityServiceImpl extends ServiceImpl<VideoActivityMapper, ActivityVideo> implements VideoActivityService {

    @Override
    public void add(ActivityVideo videoActivity) {
        this.save(videoActivity);
    }

    @Override
    public ActivityVideo getVideoActivity(Long id) {
        ActivityVideo activityVideo = this.getOne(new LambdaQueryWrapper<ActivityVideo>()
                .eq(ActivityVideo::getActivityId, id));
        if (ObjectUtils.allNull(activityVideo)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ACTIVITY_NOT_EXIST_ERROR);
        }
        return activityVideo;
    }
}
