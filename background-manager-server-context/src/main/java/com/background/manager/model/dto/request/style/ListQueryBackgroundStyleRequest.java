package com.background.manager.model.dto.request.style;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListQueryBackgroundStyleRequest {

    @ApiModelProperty("样式名称")
    private String name;

}
