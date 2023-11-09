package com.background.manager.model.dto.request.receipt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("新增发票请求体")
public class AddReceiptRequest {

    @ApiModelProperty("发票类型(0-普票，1-专票)")
    private Integer type;

    @ApiModelProperty("发票抬头")
    private String title;

    @ApiModelProperty("纳税人识别号")
    private String taxIdNumber;

    @ApiModelProperty("开户银行")
    private String accountBank;

    @ApiModelProperty("开户账号")
    private String accountNumber;

    @ApiModelProperty("注册场所地址")
    private String registerAddress;

    @ApiModelProperty("公司注册电话")
    private String companyPhone;

    @ApiModelProperty("开票金额")
    private BigDecimal amount;

    @ApiModelProperty("所属合同编号")
    private String contractCode;

    @ApiModelProperty("收件人")
    private String receiver;

    @ApiModelProperty("收件人地址")
    private String receiverAddress;

    @ApiModelProperty("邮政编码")
    private String postalCode;

    @ApiModelProperty("收件人联系方式")
    private String  receiverPhone;

    @ApiModelProperty("备注")
    private String remarks;

}
