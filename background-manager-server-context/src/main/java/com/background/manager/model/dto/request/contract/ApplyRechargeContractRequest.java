package com.background.manager.model.dto.request.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("审核用户充值请求体")
public class ApplyRechargeContractRequest {

    @ApiModelProperty("充值编号")
    private String id;

    @ApiModelProperty("是否同意/拒绝")
    private Integer rechargeStatus;

    @ApiModelProperty("备注")
    private String remark;

}
