package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("下架资源请求体")
public class updateResourceDisplayStatusRequest {

    @ApiModelProperty(value = "资源id",required = true)
    private String id;

//    @ApiModelProperty(value = "状态（OFF-关,ON-开）",required = true)
//    private String status;
}
