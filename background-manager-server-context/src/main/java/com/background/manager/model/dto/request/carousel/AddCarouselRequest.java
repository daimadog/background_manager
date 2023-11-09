package com.background.manager.model.dto.request.carousel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel("新增轮播页请求体")
public class AddCarouselRequest {
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
