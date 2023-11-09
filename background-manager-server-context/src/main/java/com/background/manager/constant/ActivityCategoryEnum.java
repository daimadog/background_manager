package com.background.manager.constant;

import com.background.manager.model.activity.ActivityResources;
import com.background.manager.model.activity.ActivityText;
import com.background.manager.model.activity.ActivityVideo;

/**
 * @Description: 活动类型枚举
 * @Author: 杜黎明
 * @Date: 2022/10/28 14:21:50
 * @Version: 1.0.0
 */
public enum ActivityCategoryEnum {

    COMPUTER_RESOURCES(1, "计算资源", ActivityResources.class),
    TEXT(2, "文本内容", ActivityText.class),
    VIDEO(3, "直播视频", ActivityVideo.class),
    ;

    private Integer type;
    private String description;
    private Class<?> clazz;

    ActivityCategoryEnum(Integer type, String description, Class clazz) {
        this.type = type;
        this.description = description;
        this.clazz = clazz;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static ActivityCategoryEnum findEnum(Integer code) {
        ActivityCategoryEnum categoryEnum = ActivityCategoryEnum.values()[code-1];
        return categoryEnum;
    }
}
