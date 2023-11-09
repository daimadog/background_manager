package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智算获取用户购买数据请求体")
public class accountStatisticsRequest extends PageRequest{

    @ApiModelProperty(value = "开始时间",required = true)
    private String startTime;

    @ApiModelProperty(value = "结束时间",required = true)
    private String endTime;


}
