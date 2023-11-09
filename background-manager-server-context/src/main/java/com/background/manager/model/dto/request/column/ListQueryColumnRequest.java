package com.background.manager.model.dto.request.column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("列表查询栏目请求体")
public class ListQueryColumnRequest {


    @ApiModelProperty("栏目名称")
    private String name;
    @ApiModelProperty("栏目状态")
    private Integer status;

}
