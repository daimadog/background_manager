package com.background.manager.handler;

import com.background.manager.constant.ActivityCategoryEnum;
import com.background.manager.model.activity.Activity;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 活动类型工厂类
 * @Author: 杜黎明
 * @Date: 2022/10/28 14:13:43
 * @Version: 1.0.0
 */
public class ActivityFactory {

    public static Activity getInstance(Integer type){
        ActivityCategoryEnum activityCategoryEnum = ActivityCategoryEnum.findEnum(type);
        try {
            return (Activity) activityCategoryEnum.getClazz().getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
