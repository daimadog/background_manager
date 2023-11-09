package com.background.manager.model.dto.request.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("新增文章请求体")
public class AddArticleRequest {

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章具体内容")
    private String content;

    @ApiModelProperty("文章背景图")
    private String imgUrl;

    @ApiModelProperty("文章描述")
    private String description;

    @ApiModelProperty("是否原创(0-原创,1-转载)")
    private Integer isOriginal;

    @ApiModelProperty("外部链接")
    private String href;

    @ApiModelProperty("来源")
    private String source;

    @ApiModelProperty("是否置顶")
    private Integer isTop;

    @ApiModelProperty("所属栏目ID")
    private Long columnId;

    @ApiModelProperty("新闻发布时间")
    private String articleTime;

}
