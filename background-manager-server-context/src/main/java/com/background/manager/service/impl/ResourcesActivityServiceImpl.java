package com.background.manager.service.impl;

import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.ResourcesMapper;
import com.background.manager.model.activity.ActivityResources;
import com.background.manager.service.ResourcesActivityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;


@Service
public class ResourcesActivityServiceImpl extends ServiceImpl<ResourcesMapper, ActivityResources> implements ResourcesActivityService {

    @Override
    public void add(ActivityResources resourcesActivity) {
        this.save(resourcesActivity);
    }

    @Override
    public ActivityResources getResourcesActivity(Long id) {
        ActivityResources activityResources = this.getOne(new LambdaQueryWrapper<ActivityResources>()
                .eq(ActivityResources::getActivityId, id));
        if (ObjectUtils.allNull(activityResources)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ACTIVITY_NOT_EXIST_ERROR);
        }
        return activityResources;
    }


}
