package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeleteUserRequest {

    @ApiModelProperty("用户编号")
    private String userId;

}
