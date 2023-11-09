package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("计费策略请求体")
public class billingStrategyRequest extends PageRequest{

    @ApiModelProperty(value = "状态")
    private Boolean status;

    @ApiModelProperty(value = "计费策略名称")
    private String nameLike;

    @ApiModelProperty(value = "分类（compute计算，blockStorage存储，cloudService产品服务，enterpriseIntelligencet:EI企业智能）")
    private String category;

}
