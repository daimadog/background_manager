package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("公告管理实体类")
public class BackgroundAnnouncement extends ContextBasePO{
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 公告类型（0-综合公告，1-升级公告）
     */
    private Integer type;
    /**
     * 发布状态(0-未发布，1-已发布)
     */
    private Integer applyStatus;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 公告时间
     */
    private String announcementTime;

}
