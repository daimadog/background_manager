package com.background.manager.model.dto.response.receipt;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@ApiModel("查询我的合同记录DTO")
public class BackgroundContractUserDigestDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("合同名称")
    private String name;

    @ApiModelProperty("合同编号")
    private String code;

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

}
