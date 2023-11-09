package com.background.manager.model.dto.request.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@ApiModel("修改合同请求体")
public class ModifyContractRequest {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("合同名称")
    private String name;

    @ApiModelProperty("合同编号")
    private String code;

    @ApiModelProperty("合同签订时间")
    private Date signTime;

    @ApiModelProperty("甲方负责人")
    private String firstParty;

    @ApiModelProperty("乙方负责人")
    private String secondParty;

    @ApiModelProperty("合同所属账户")
    private String accountId;

    @ApiModelProperty("合同开始时间")
    private Date startTime;

    @ApiModelProperty("合同结束时间")
    private Date endTime;

    @ApiModelProperty("合同金额")
    @Digits(integer = 9,fraction = 2,message = "充值金额最多为9位，小数点最多有2位")
    @DecimalMin(value = "0",message ="充值金额必须大于0" )
    @NotNull(message = "请输入合法的充值金额")
    private BigDecimal amount;

    @ApiModelProperty("信用额度")
    @Digits(integer = 9,fraction = 2,message = "充值金额最多为9位，小数点最多有2位")
    @DecimalMin(value = "0",message ="充值金额必须大于0" )
    @NotNull(message = "请输入合法的充值金额")
    private BigDecimal creditLimit;

    @ApiModelProperty("合同状态 0-未审核，1-执行中，2-执行结束")
    private Integer status;

    @ApiModelProperty("开票状态 0-未开票，1-开票中，2-已开票")
    private Integer receiptStatus;

    @ApiModelProperty("合同附件文件地址")
    private String contractFileUrl;

}
