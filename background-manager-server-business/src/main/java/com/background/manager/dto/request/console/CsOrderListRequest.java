package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("超算用户订单列表请求体")
public class CsOrderListRequest {

    @ApiModelProperty("账户名称")
    private String accountName;
    @ApiModelProperty(value = "充值类型，ORDER-订单充值，ADD-补充费用，FREE-赠送资源，WRITE-OFF-欠费冲销")
    private String rechargeType;
    @ApiModelProperty(value = "订单编号")
    private String orderId;
    @ApiModelProperty(value = "页码，默认值为1",required = true)
    private int pageNum;
    @ApiModelProperty(value = "页大小", required = true)
    private int pageSize;
    @ApiModelProperty(value = "开始时间查询，格式2022-12-01 00:00:00",required = true)
    private String startTime;
    @ApiModelProperty(value = "结束时间查询，格式2022-12-01 00:00:00",required = true)
    private String endTime;

}
