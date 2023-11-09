package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("上架资源请求体")
public class updateResourceDisplayRequest {

    @ApiModelProperty(value = "资源id",required = true)
    private String id;

    @ApiModelProperty(value = "标签（HOT, RECOMMEND）")
    private String label;

    @ApiModelProperty(value = "资源上架的正式集群",required = true)
    private String formalClusterInfo;

    @ApiModelProperty(value = "资源上架的试用集群")
    private String trialClusterInfo;

    @ApiModelProperty(value = "资源下架的正式集群")
    private String delFormalClusters;

    @ApiModelProperty(value = "资源下架的试用集群")
    private String delTrialClusters;

}
