package com.background.manager.model.dto.request.activity;

import java.util.Map;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("新增活动请求体")
public class AddActivityRequest {

    @ApiModelProperty("活动标题")
    private String title;

    @ApiModelProperty("活动缩略图")
    private String imgUrl;

    @ApiModelProperty("活动描述")
    private String description;

    @ApiModelProperty("活动所属栏目ID")
    private Long columnId;

    @ApiModelProperty("活动内容")
    private String content;

    @ApiModelProperty("是否原创(0-转载,1-原创)")
    private Integer isOriginal;

    @ApiModelProperty("外部链接")
    private String contentUrl;

    @ApiModelProperty("活动类型（0-线上活动，1-线下活动）")
    private Integer type;

    @ApiModelProperty("活动状态")
    private Integer status;

    @ApiModelProperty("是否置顶")
    private Integer isTop;

    @ApiModelProperty("具体活动内容（map集合形式）")
    private Map<String,Object> context;
}
