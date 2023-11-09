package com.background.manager.model.dto.request.news;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询文章请求体")
public class PageQueryArticleRequest extends BasePageDTO {

    @ApiModelProperty("文章关键词")
    private String keywords;

    @ApiModelProperty("所属栏目ID")
    private Long columnId;
}
