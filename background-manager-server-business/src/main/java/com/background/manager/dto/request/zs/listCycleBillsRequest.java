package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智算获取客户账单请求体")
public class listCycleBillsRequest extends PageRequest{

    @ApiModelProperty(value = "分组标识：cycle按周期分组/customer按客户分组",required = true)
    private String groupByFlag;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "付费类型(SubscriptionOrder：预付费，PayAsYouGoBill：后付费)")
    private String subscriptionType;

    @ApiModelProperty(value = "查询类型（resource：资源计费，service：服务计费）")
    private String priceType;

}
