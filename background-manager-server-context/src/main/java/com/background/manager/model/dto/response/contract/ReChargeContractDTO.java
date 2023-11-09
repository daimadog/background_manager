package com.background.manager.model.dto.response.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("合同充值列表DTO")
public class ReChargeContractDTO {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("合同名称")
    private String contractName;
    @ApiModelProperty("合同编码")
    private String contractCode;
    @ApiModelProperty("合同金额")
    private BigDecimal contractAmount;
    @ApiModelProperty("充值金额")
    private BigDecimal rechargeAmount;
    @ApiModelProperty("充值时间")
    private LocalDateTime rechargeTime;
    @ApiModelProperty("充值状态(0-审核中,1-同意,2-拒绝)")
    private Integer rechargeStatus;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
