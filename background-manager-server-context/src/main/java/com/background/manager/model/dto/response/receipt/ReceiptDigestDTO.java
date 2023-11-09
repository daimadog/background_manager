package com.background.manager.model.dto.response.receipt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("发票概要DTO")
public class ReceiptDigestDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("开票时间")
    private LocalDateTime receiptTime;

    @ApiModelProperty("申请开票人")
    private String username;

    @ApiModelProperty("发票类型(0-普票，1-专票)")
    private Integer type;

    @ApiModelProperty("发票状态")
    private Integer status;

    @ApiModelProperty("发票抬头")
    private String title;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("合同名称")
    private String contractName;
}
