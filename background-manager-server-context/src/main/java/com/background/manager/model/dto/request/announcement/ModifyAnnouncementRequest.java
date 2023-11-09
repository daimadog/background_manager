package com.background.manager.model.dto.request.announcement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改公告请求体")
public class ModifyAnnouncementRequest {

    @ApiModelProperty("公告主键ID")
    private Long id;

    @ApiModelProperty("公告发布状态(0-未发布，1-发布)")
    private Integer applyStatus;

}
