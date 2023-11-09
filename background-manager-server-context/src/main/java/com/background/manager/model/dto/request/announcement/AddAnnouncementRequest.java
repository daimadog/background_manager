package com.background.manager.model.dto.request.announcement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ApiModel("新增公告请求体")
public class AddAnnouncementRequest {

    @ApiModelProperty("公告类型（0-综合公告，1-升级公告）")
    @NotNull(message = "公告类型不能为空")
    private Integer type;

    @ApiModelProperty("公告标题")
    @NotNull(message = "公告标题不能为空")
    private String title;

    @ApiModelProperty("公告内容")
    private String content;

    @ApiModelProperty("公告时间")
    private String announcementTime;

}
