package com.background.manager.service.impl;

import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.TextActivityMapper;
import com.background.manager.model.activity.ActivityText;
import com.background.manager.service.TextActivityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class TextActivityServiceImpl extends ServiceImpl<TextActivityMapper,ActivityText> implements TextActivityService {

    @Override
    public void add(ActivityText textActivity) {
       this.save(textActivity);
    }

    @Override
    public ActivityText getTextActivity(Long id) {
        ActivityText activityText = this.getOne(new LambdaQueryWrapper<ActivityText>()
                .eq(ActivityText::getActivityId, id));
        if (ObjectUtils.allNull(activityText)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ACTIVITY_NOT_EXIST_ERROR);
        }
        return activityText;
    }


}
