package com.background.manager.model.dto.request.contract;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("审核合同请求体")
public class AuditContactRequest {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("合同状态 0-未审核，1-执行中，2-执行结束")
    private Integer status;

}
