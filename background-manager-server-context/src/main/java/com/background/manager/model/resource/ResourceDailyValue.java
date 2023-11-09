package com.background.manager.model.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("单日资源值集合")
public class ResourceDailyValue {

    @ApiModelProperty("横坐标-时间（HH::mm）")
    private String time;
    @ApiModelProperty("资源数量")
    private Integer number;
    @ApiModelProperty("占用率%")
    private BigDecimal percentage;

}
