package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智算更新产品状态请求体")
public class zsUpdateProductStatusRequest {

    @ApiModelProperty(value = "产品id",required = true)
    private  Long id;

    @ApiModelProperty(value = "产品状态(using已上架，nousing待上架)",required = true)
    private String status;

}
