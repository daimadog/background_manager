package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智算获取客户收支明细请求体")
public class accountDealsRequest extends  PageRequest {

    @ApiModelProperty(value = "账户id",required = true)
    private String accountSid;

    @ApiModelProperty("交易开始时间")
    private String startTime;

    @ApiModelProperty("交易结束时间")
    private String endTime;

    @ApiModelProperty("客户名称")
    private String accountNameLike;

    @ApiModelProperty("交易编号")
    private String flowNoLike;

    @ApiModelProperty("收支类型，in（收入），out （支出）")
    private String type;

    @ApiModelProperty("交易类型，charge（充值），pay（消费），refund（退款）")
    private String tradeType;

    @ApiModelProperty("充值类型(platform（平台）" +
            "acctCash（用户余额）" +
            "accCredit（信用额度余额）" +
            "balanceCash（充值现金券）" +
            "deductBalanceCash（抵扣现金券）)")
    private String tradeChannel;

    @ApiModelProperty("交易流水号")
    private String tradeNoLike;

    @ApiModelProperty("账单号")
    private String orderNoLike;

    @ApiModelProperty("排序字段")
    private String sortdatafield;

    @ApiModelProperty("排序字符串（desc：倒序，asc：正序）")
    private String sortorder;

}
