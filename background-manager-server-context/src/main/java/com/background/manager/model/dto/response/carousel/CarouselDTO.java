package com.background.manager.model.dto.response.carousel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("轮播页DTO")
public class CarouselDTO {
    /**
     * ID主键
     */
    private Long id;
    /**
     * 轮播页标题
     */
    private String title;
    /**
     * 轮播页文字内容
     */
    private String content;
    /**
     * 轮播页图片地址
     */
    private String imageUrl;
    /**
     * 外部链接地址
     */
    private String externalUrl;
}
