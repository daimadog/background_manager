package com.background.manager.service;

import com.background.manager.model.activity.ActivityText;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TextActivityService extends IService<ActivityText> {

     /**
      * Description: 新增文本activity
      * @param request 文本活动
      * @author 杜黎明
      * @date 2022/10/28 15:35:40
      */
     void add(ActivityText request);

    ActivityText getTextActivity(Long id);
}
