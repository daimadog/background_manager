package com.background.manager.model.dto.response.announcement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("首页公告列表DTO")
public class BackgroundAnnouncementDigestDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("公告类型（0-综合公告，1-升级公告）")
    private Integer type;

    @ApiModelProperty("发布状态(0-未发布，1-已发布)")
    private Integer applyStatus;

    @ApiModelProperty("公告标题")
    private String title;

    @ApiModelProperty("公告内容")
    private String content;

    @ApiModelProperty("公告时间")
    private String announcementTime;

}
