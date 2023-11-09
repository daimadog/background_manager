package com.background.manager.model.dto.response.style;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BackgroundStyleDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("样式风格")
    private String identify;

    @ApiModelProperty("颜色")
    private String color;

}



