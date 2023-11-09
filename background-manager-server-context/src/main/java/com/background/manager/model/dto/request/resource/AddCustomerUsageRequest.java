package com.background.manager.model.dto.request.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
@ApiModel("新增客户情况请求体")
public class AddCustomerUsageRequest {

    @ApiModelProperty("客户种类 0-类型，1-行业")
    private Integer type;
    @ApiModelProperty("客户名称")
    private String name;
    @ApiModelProperty("客户数量")
    private Integer value;
    @ApiModelProperty("客户占比")
    private BigDecimal proportion;

}
