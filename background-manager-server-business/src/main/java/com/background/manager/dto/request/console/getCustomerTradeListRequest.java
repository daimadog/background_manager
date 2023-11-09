package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取客户收支明细请求体")
public class getCustomerTradeListRequest {
    @ApiModelProperty(value = "用户主键ID",required = true)
    private String userId;
    @ApiModelProperty(value = "交易类型：充值-RECHARGE，退费-REFUND，消费-CONSUME，扣费-DEDUCT",required = true)
    private String tradeType;
    @ApiModelProperty(value = "页码，默认值为1",required = true)
    private int pageNum;
    @ApiModelProperty(value = "页大小", required = true)
    private int pageSize;
    @ApiModelProperty(value = "开始时间查询，格式2022-12-01 00:00:00",required = true)
    private String startTime;
    @ApiModelProperty(value = "结束时间查询，格式2022-12-01 00:00:00",required = true)
    private String endTime;


}
