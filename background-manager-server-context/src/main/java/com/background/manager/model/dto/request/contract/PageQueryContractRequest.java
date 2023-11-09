package com.background.manager.model.dto.request.contract;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@ApiModel("分页查询合同请求体")
public class PageQueryContractRequest extends BasePageDTO {

    @ApiModelProperty("合同名称")
    private String name;

    @ApiModelProperty("合同编号")
    private String code;

    @ApiModelProperty("合同签订时间")
    private Date signTime;

    @ApiModelProperty("合同所属账户")
    private String accountId;

    @ApiModelProperty("合同开始时间")
    private Date startTime;

    @ApiModelProperty("合同结束时间")
    private Date endTime;

    @ApiModelProperty("合同金额")
    private BigDecimal amount;

    @ApiModelProperty("合同状态 0-未审核，1-执行中，2-执行结束")
    private Integer status;


}
