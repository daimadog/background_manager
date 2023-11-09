package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("合同充值记录")
public class ContractApplicationRecord {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 账户ID
     */
    private String accountId;
    /**
     * 合同id
     */
    private Integer contractId;
    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;
    /**
     * 充值时间
     */
    private LocalDateTime rechargeTime;
    /**
     * 充值状态
     */
    private Integer rechargeStatus;
    /**
     * 备注
     */
    private String remark;

    private String creator;

    private LocalDateTime createTime;

    private String modifier;

    private LocalDateTime updateTime;

}
