package com.background.manager.model.dto.request.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("修改集群资源请求体")
public class ModifyResourceRequest {

    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("集群资源类型(0-AI集群资源，1-HPC集群资源)")
    private Integer type;
    @ApiModelProperty("资源指标")
    private String resourceIndex;
    @ApiModelProperty("资源名称")
    private String resourceName;
    @ApiModelProperty("资源单位")
    private String unit;
    @ApiModelProperty("资源值")
    private BigDecimal value;

}
