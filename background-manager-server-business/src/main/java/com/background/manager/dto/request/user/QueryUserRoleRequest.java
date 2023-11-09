package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询用户角色请求体")
public class QueryUserRoleRequest {

    @ApiModelProperty("用户编号")
    private String userId;
}
