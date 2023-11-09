package com.background.manager.dto.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("修改角色信息请求体")
public class ModifyRoleRequest {

    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色英文名称")
    private  String enName;

    @ApiModelProperty("权限菜单id集合")
    private List<Long> permissionIds;

}
