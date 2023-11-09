package com.background.manager.model.dto.response.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("客户情况DTO")
public class CustomerUsageDTO {

    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("客户种类 0-类型，1-行业")
    private Integer type;
    @ApiModelProperty("客户名称")
    private String name;
    @ApiModelProperty("客户数量")
    private Integer value;
    @ApiModelProperty("客户占比")
    private BigDecimal proportion;

}
