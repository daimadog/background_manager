package com.background.manager.model.dto.response.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("文章详情DTO")
public class ArticleDTO {

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("所属栏目ID")
    private Long columnId;

    @ApiModelProperty("文章状态(0-正常，1-冻结)")
    private Integer status;

    @ApiModelProperty("文章描述")
    private String description;

    @ApiModelProperty("文章缩略图")
    private String imgUrl;

    @ApiModelProperty("是否原创(0-原创,1-转载)")
    private Integer isOriginal;

    @ApiModelProperty("来源")
    private String source;

    @ApiModelProperty("外部链接")
    private String href;

    @ApiModelProperty("发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("发布人")
    private String creator;

    @ApiModelProperty("是否置顶")
    private Integer isTop;

    @ApiModelProperty("新闻发布时间")
    private String articleTime;

}
