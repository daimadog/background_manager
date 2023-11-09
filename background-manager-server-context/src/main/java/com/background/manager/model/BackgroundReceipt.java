package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: 杜黎明
 * @Date: 2023/04/13 09:58:52
 * @Version: 1.0.0
 */
@Data
@ApiModel("发票管理")
public class BackgroundReceipt extends ContextBasePO {

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
    private Long contractId;

    @ApiModelProperty("开票时间")
    private LocalDateTime receiptTime;

    @ApiModelProperty("发票状态")
    private Integer status;

    @ApiModelProperty("发票类型(0-普票，1-专票)")
    private Integer type;

    @ApiModelProperty("发票文件地址")
    private String receiptUrl;

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

    @ApiModelProperty("明细")
    private String detail;

    @ApiModelProperty("发票申请状态")
    private Integer applyStatus;

}
