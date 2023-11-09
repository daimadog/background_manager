package com.background.manager.model.dto.request.carousel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel("修改轮播页请求体")
public class ModifyCarouselRequest {

    @ApiModelProperty("轮播页主键ID")
    private Long id;

    @ApiModelProperty("轮播页标题")
    private String title;

    @ApiModelProperty("轮播页文字内容")
    private String content;

    @ApiModelProperty("轮播页图片地址")
    private String imageUrl;

    @ApiModelProperty("外部链接地址")
    private String externalUrl;

}
