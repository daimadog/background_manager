package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("资源上架列表请求体")
public class queryResourceDisplayListRequest {

    @ApiModelProperty(value = "页码，默认值为1",required = true)
    private String start;

    @ApiModelProperty(value = "页大小", required = true)
    private String limit;

    @ApiModelProperty(value = "集群id",required = true)
    private String centerId;

    @ApiModelProperty("标签-RECOMMEND，HOT")
    private String label;

    @ApiModelProperty("资源名称")
    private String resourceId;

    @ApiModelProperty("技术规格")
    private String specifications;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("类型")
    private String type;
}
