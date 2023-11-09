package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取资源目录请求体")
public class getItemRequest {

    @ApiModelProperty(value = "页数",required = true)
    private Integer start;

    @ApiModelProperty(value = "条数",required = true)
    private Integer limit;

    @ApiModelProperty(value = "资源状态:启用-ENABLE,禁用：DISABLE")
    private String status;

    @ApiModelProperty(value = "资源类型 P300")
    private String type;

    @ApiModelProperty(value = "服务类型(存储服务：storage,计算服务：compute)")
    private String service;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源编号")
    private String resourceId;

    @ApiModelProperty(value = "技术规格")
    private String specifications;

}
