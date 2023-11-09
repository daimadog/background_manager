package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智算获取收支明细请求体")
public class accountDealsListRequest extends PageRequest {

    @ApiModelProperty(value = "交易开始时间")
    private String startTime;

    @ApiModelProperty(value = "交易结束时间")
    private String endTime;

    @ApiModelProperty(value = "账户名称")
    private String accountNameLike;

    @ApiModelProperty(value = "交易编号")
    private String flowNoLike;

    @ApiModelProperty(value = "收支类型，in（收入） out （支出）")
    private String type;

    @ApiModelProperty(value = "交易类型，charge（充值），pay（消费），refund（退款）")
    private  String tradeType;

    @ApiModelProperty(value = "充值类型(platform（平台）" +
            "acctCash（用户余额）" +
            "accCredit（信用额度余额）" +
            "balanceCash（充值现金券）" +
            "deductBalanceCash（抵扣现金券）)")
    private String tradeChannel;

    @ApiModelProperty(value = "交易流水号")
    private String tradeNoLike;

    @ApiModelProperty(value = "订单号")
    private String orderNoLike;

    @ApiModelProperty(value = "账单号")
    private String billNoLike;

    @ApiModelProperty(value = "排序字段")
    private String sortdatafield;

    @ApiModelProperty(value = "排序方法字符串（desc：倒序，asc：正序）")
    private String sortorder;





}
