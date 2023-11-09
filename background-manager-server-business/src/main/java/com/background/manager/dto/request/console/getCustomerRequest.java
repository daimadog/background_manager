package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取前 10 用户购买金额总额")
public class getCustomerRequest {

    @ApiModelProperty(value = "开始时间",required = true)
    private String startTime;

    @ApiModelProperty(value = "结束时间",required = true)
    private String endTime;

}
