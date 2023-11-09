package com.background.manager.dto.request.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询权限菜单请求体")
public class QueryPermsRequest {

    @ApiModelProperty("权限名称")
    private String menuName;

    @ApiModelProperty("权限状态")
    private String status;

}
