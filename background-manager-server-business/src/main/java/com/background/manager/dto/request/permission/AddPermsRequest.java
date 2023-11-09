package com.background.manager.dto.request.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("新增权限菜单请求体")
public class AddPermsRequest {

    @ApiModelProperty("上级菜单")
    private String parentId;

    @ApiModelProperty("权限名称")
    private String menuName;

    @ApiModelProperty("权限类型")
    private Integer type;

    @ApiModelProperty("权限状态")
    private Integer status;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("排序权重")
    private Integer weight;

}
