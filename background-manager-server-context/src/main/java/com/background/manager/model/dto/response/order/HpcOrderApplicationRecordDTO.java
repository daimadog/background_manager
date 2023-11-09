package com.background.manager.model.dto.response.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("HPC订单申请记录DTO")
public class HpcOrderApplicationRecordDTO {

    @ApiModelProperty("申请记录ID")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("订单ID")
    private String orderId;

    @ApiModelProperty("服务类型")
    private String serviceType;

    @ApiModelProperty("订单支付金额")
    private BigDecimal amount;

    @ApiModelProperty("申请类型(0-续订，1-退订)")
    private Integer applyType;

    @ApiModelProperty("申请状态(0-审核中,1-同意,2-拒绝)")
    private Integer applyStatus;

    @ApiModelProperty("订单申请时间")
    private LocalDateTime applyTime;

    @ApiModelProperty("续费时间")
    private String renewalTime;

    @ApiModelProperty("备注")
    private String remark;
}
