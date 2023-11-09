package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取产品月销售额数据请求体")
public class orderStatisticsRequest {

    @ApiModelProperty(value = "开始时间",required = true)
    private String startTime;

    @ApiModelProperty(value = "结束时间",required = true)
    private String endTime;

}
