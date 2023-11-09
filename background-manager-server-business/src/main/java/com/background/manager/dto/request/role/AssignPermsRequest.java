package com.background.manager.dto.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("权限分配请求体")
public class AssignPermsRequest {

    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("权限菜单id集合")
    private List<Long> permissionIds;

}
