package com.background.manager.dto.response.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("权限菜单DTO")
public class PermissionDTO {

    @ApiModelProperty("权限id")
    private Long id;

    @ApiModelProperty("权限菜单名称")
    private String menuName;

    @ApiModelProperty("权限路径")
    private String path;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("父菜单ID")
    private Long parentId;

    @ApiModelProperty("子节点权限")
    private List<PermissionDTO> children;
}
