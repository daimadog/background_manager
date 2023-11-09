package com.background.manager.model.dto.request.announcement;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询公告请求体")
public class PageQueryAnnouncementRequest extends BasePageDTO {

    @ApiModelProperty("公告类型（0-综合公告，1-升级公告）")
    private Integer type;

    @ApiModelProperty("发布状态(0-未发布，1-已发布)")
    private Integer applyStatus;

    @ApiModelProperty("公告标题")
    private String title;

    @ApiModelProperty("公告时间")
    private String announcementTime;

}
