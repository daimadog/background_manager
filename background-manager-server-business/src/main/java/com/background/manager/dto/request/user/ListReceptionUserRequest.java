package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("列表查询用户列表请求体")
public class ListReceptionUserRequest {

    @ApiModelProperty(value = "用户名",required = false)
    private String username;

}
