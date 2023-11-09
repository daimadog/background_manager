package com.background.manager.model.dto.response.receipt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@ApiModel("开票记录DTO")
public class ReceiptRecordDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("开票时间")
    private LocalDateTime receiptTime;

    @ApiModelProperty("开票金额")
    private BigDecimal amount;

    @ApiModelProperty("收件人")
    private String receiver;

    @ApiModelProperty("发票类型(0-普票，1-专票)")
    private Integer type;

    @ApiModelProperty("发票状态")
    private Integer status;

    @ApiModelProperty("发票抬头")
    private String title;

    @ApiModelProperty("备注")
    private String remarks;
}
