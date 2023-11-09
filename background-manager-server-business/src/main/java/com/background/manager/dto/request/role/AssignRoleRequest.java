package com.background.manager.dto.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("分配角色请求体")
public class AssignRoleRequest {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("角色id")
    private List<Long> roleId;
}
