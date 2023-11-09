package com.background.manager.dto.response.role;

import com.background.manager.dto.response.permission.PermissionDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("角色详情DTO")
public class RoleDetailDTO {

    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色英文名")
    private String enName;
    @ApiModelProperty("权限集合")
    private List<PermissionDTO> permissionDTOList;

}
