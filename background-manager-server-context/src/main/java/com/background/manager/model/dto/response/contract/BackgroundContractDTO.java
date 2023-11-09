package com.background.manager.model.dto.response.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@ApiModel("合同详情DTO")
public class BackgroundContractDTO {

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
    private String companyName;

    @ApiModelProperty("合同开始时间")
    private Date startTime;

    @ApiModelProperty("合同结束时间")
    private Date endTime;

    @ApiModelProperty("合同金额")
    private BigDecimal amount;

    @ApiModelProperty("信用额度")
    private BigDecimal creditLimit;

    @ApiModelProperty("合同状态 0-未审核，1-执行中，2-执行结束")
    private Integer status;

    @ApiModelProperty("开票状态 0-未开票，1-开票中，2-已开票")
    private Integer receiptStatus;

    @ApiModelProperty("合同附件文件地址")
    private String contractFileUrl;

}
