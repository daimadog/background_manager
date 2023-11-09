package com.background.manager.model.dto.request.carousel;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询轮播页请求体")
public class PageQueryCarouselRequest  extends BasePageDTO {

    @ApiModelProperty("轮播页标题")
    private String title;
}
