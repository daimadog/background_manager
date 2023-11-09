package com.background.manager.model.dto.request.resource;

import com.background.manager.model.resource.ResourceDailyValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("新增单日资源使用情况")
public class AddResourceDailyUsage {

    @ApiModelProperty("资源日期")
    private Date  date;
    @ApiModelProperty("类型 0-AI，1-HPC")
    private Integer type;
    @ApiModelProperty("单日资源值集合")
    private List<ResourceDailyValue> values;

}
