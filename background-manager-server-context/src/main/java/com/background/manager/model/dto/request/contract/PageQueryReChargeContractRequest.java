package com.background.manager.model.dto.request.contract;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询合同充值记录请求体")
public class PageQueryReChargeContractRequest extends BasePageDTO {

    @ApiModelProperty("合同ID")
    private String contractId;

    @ApiModelProperty("充值状态")
    private Integer rechargeStatus;

}
