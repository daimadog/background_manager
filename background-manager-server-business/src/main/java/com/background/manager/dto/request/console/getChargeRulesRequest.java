package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取计费规则请求体")
public class getChargeRulesRequest {

    @ApiModelProperty(value = "页数",required = true)
    private String start;

    @ApiModelProperty(value = "条数",required = true)
    private String limit;

    @ApiModelProperty(value = "区域 id，空为平台计费规则",required = true)
    private String areaId;

    @ApiModelProperty(value = "服务类型(存储：storage;共享：share;独占：contract)")
    private String type;

    @ApiModelProperty(value = "资源状态:启用-ENABLE,禁用：DISABLE")
    private String status;

    @ApiModelProperty(value = "资源编号")
    private String resourceId;

}
