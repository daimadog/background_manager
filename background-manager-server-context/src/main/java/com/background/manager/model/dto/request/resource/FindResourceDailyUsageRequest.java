package com.background.manager.model.dto.request.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Date;

@Data
@ApiModel("查找单日资源使用情况请求体")
public class FindResourceDailyUsageRequest {

    @ApiModelProperty("日期")
    private Date date;
    @ApiModelProperty("类型 0-AI，1-HPC")
    private Integer type;

}
