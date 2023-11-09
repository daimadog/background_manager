package com.background.manager.handler;

import cn.hutool.core.util.ObjectUtil;
import com.background.manager.model.activity.Activity;
import org.checkerframework.checker.units.qual.A;

import java.util.Map;

/**
 * @Description: 活动基本配置处理器
 * @Author: 杜黎明
 * @Date: 2022/10/28 14:40:55
 * @Version: 1.0.0
 */
public abstract class ActivityHandler {

    public ActivityHandler nextHandler;

    public void build(Integer activityType, Map<String, Object> context) {
        process(activityType,context);
        if (ObjectUtil.isNotNull(nextHandler)){
            nextHandler.build(activityType, context);
        }
    }

    /**
     * Description: 执行配置信息处理
     * @param activityType 活动类型
     * @return {@link Void }
     * @author 杜黎明
     * @date 2022/10/28 14:42:18
     */
    public abstract void process(Integer activityType, Map<String, Object> context);

}
