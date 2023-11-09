package com.background.manager.model.dto.request.style;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改基础设施请求体")
public class ModifyInfrastructureRequest {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("数量")
    private Integer number;

}
