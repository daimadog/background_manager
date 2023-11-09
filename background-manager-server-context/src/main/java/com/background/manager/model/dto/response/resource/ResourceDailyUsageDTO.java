package com.background.manager.model.dto.response.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
@ApiModel("资源使用情况")
public class ResourceDailyUsageDTO {

    @ApiModelProperty("日期")
    private Date date;
    @ApiModelProperty("类型 0-AI，1-HPC")
    private Integer type;
    @ApiModelProperty("资源值")
    List<ResourceDailyValueDTO> value;
}
