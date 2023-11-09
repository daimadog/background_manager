package com.background.manager.dto.request.user;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiOperation("分页查询用户信息请求体")
public class PageQueryUserInfoRequest extends BasePageDTO {

    @ApiModelProperty("登录账号id")
    private Long userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("管理员标识")
    private Integer administratorFlag;
    
}
