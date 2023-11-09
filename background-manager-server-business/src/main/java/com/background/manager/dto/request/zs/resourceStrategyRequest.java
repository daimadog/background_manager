package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "智算获取实例管理")
public class resourceStrategyRequest extends PageRequest{

//    @ApiModelProperty(value = "是否为运营控制台")
//    private Boolean mgtConsole;

    @ApiModelProperty(value = "产品名称")
    private String productNameLike;

    @ApiModelProperty(value = "状态（pending申请中，renewing续订中，" +
            "expired已到期，unsubscribed已退订" +
            "unsubscribing退订中，soonExpire即将到期）")
    private String status;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "产品类型（OBS:对象存储OBS" +
            "ModelArts:昇腾Modelarts共享资源池" +
            "DRP:昇腾Modelarts专属资源池）")
    private String productType;

    @ApiModelProperty(value = "所属分销商")
    private String distributorName;

    @ApiModelProperty(value = "申请客户")
    private String accountNameLike;
}
