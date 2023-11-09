package com.background.manager.model.dto.request.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("审核订单请求体")
public class AuditOrderRequest {

    @ApiModelProperty("申请订单ID主键")
    private String id;

    @ApiModelProperty("申请状态(1-同意,2-拒绝)")
    private Integer applyStatus;

    @ApiModelProperty("备注")
    private String remark;

}
