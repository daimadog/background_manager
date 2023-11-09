package com.background.manager.model.dto.request.activity;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询活动请求体")
public class PageQueryActivityRequest extends BasePageDTO {

    @ApiModelProperty("活动标题")
    private String title;

    @ApiModelProperty("栏目编号")
    private Long columnId;

    @ApiModelProperty("活动类型（0-线上活动，1-线下活动）")
    private Integer type;

}
