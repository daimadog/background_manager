package com.background.manager.model.dto.request.style;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("列表查询基础设施请求体")
public class ListInfrastructureRequest {

    @ApiModelProperty("基础设施名称")
    private String name;


}
