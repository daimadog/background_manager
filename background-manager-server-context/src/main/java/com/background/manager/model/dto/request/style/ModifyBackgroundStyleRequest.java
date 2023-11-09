package com.background.manager.model.dto.request.style;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ModifyBackgroundStyleRequest {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("颜色")
    private String color;

}
