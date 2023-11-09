package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("更新计费策略状态")
public class updateBillingStrategyRequest {

    @ApiModelProperty(value = "策略id")
    private Long id;

    @ApiModelProperty(value = "策略状态(disable禁用、enable启用)")
    private String status;

}
