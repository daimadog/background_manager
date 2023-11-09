package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("AI订单申请记录")
public class AiOrderApplicationRecord {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 账户ID
     */
    private String accountId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 产品
     */
    private String productName;
    /**
     * 支付金额
     */
    private BigDecimal finalCost;
    /**
     * 申请类型(0-续订，1-退订)
     */
    private Integer applyType;
    /**
     * 申请状态(0-审核中,1-同意,2-拒绝)
     */
    private Integer applyStatus;
    /**
     * 订单申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 续费时间
     */
    private String renewalTime;
    /**
     * 备注
     */
    private String remark;

    private String creator;

    private LocalDateTime createTime;

    private String modifier;

    private LocalDateTime updateTime;

}
