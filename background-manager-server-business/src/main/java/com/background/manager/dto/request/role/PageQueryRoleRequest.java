package com.background.manager.dto.request.role;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiOperation("分页查询角色请求体")
public class PageQueryRoleRequest extends BasePageDTO {

    @ApiModelProperty("查询条件1:角色名称")
    private String roleName;
    @ApiModelProperty("查询条件2:角色状态")
    private Integer status;
}
