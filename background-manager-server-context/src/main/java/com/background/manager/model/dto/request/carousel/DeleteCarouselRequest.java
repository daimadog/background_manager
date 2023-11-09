package com.background.manager.model.dto.request.carousel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("删除轮播页请求体")
public class DeleteCarouselRequest {

    @ApiModelProperty("轮播页主键ID")
    private Long id;

}
