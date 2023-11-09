package com.background.manager.dto.response.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("分页查询权限菜单DTO")
public class PermissionDigestDTO {

    @ApiModelProperty("权限id")
    private Long id;
    @ApiModelProperty("权限名称")
    private String menuName;
    @ApiModelProperty("权限类型(1-目录, 2-菜单,3-按钮,4-按钮)")
    private Integer type;
    @ApiModelProperty("父菜单ID")
    private Long parentId;
    @ApiModelProperty("权限图标")
    private String icon;
    @ApiModelProperty("路由地址")
    private String path;
    @ApiModelProperty("权限标识")
    private String perms;
    @ApiModelProperty("权限状态")
    private Integer status;
    @ApiModelProperty("子权限菜单")
    private List<PermissionDigestDTO> children;
}
