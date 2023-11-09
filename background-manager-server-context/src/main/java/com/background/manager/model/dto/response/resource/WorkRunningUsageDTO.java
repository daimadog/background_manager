package com.background.manager.model.dto.response.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("运行节点DTO")
public class WorkRunningUsageDTO {

    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("作业名")
    private String name;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("客户名称")
    private String customerName;
    @ApiModelProperty("使用情况")
    private BigDecimal workUsage;
    @ApiModelProperty("状态0-运行，1-挂起，2-排队，3-其他")
    private Integer status;
    @ApiModelProperty("运行节点")
    private String runningNode;
    @ApiModelProperty("节点经度")
    private String latitude;
    @ApiModelProperty("节点维度")
    private String longitude;

}
