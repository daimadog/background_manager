package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页请求")
public class PageRequest extends CommonRequest {

    @ApiModelProperty(value = "页码，默认值为1",required = true)
    private int pageNum;

    @ApiModelProperty(value = "页大小", required = true)
    private int pageSize;

}
