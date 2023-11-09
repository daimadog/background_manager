package com.background.manager.model.dto.request.receipt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("审核发票请求体")
public class AuditReceiptRequest {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("申请状态")
    private Integer applyStatus;

    @ApiModelProperty("备注")
    private String remarks;

}
