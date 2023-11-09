package com.background.manager.service;

import com.background.manager.model.activity.ActivityVideo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface VideoActivityService extends IService<ActivityVideo> {

    void add(ActivityVideo videoActivity);

    ActivityVideo getVideoActivity(Long id);
}
