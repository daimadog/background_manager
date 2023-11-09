package com.background.manager.service;

import com.background.manager.model.activity.ActivityResources;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ResourcesActivityService extends IService<ActivityResources> {

    void add(ActivityResources resourcesActivity);

    ActivityResources getResourcesActivity(Long id);
}
