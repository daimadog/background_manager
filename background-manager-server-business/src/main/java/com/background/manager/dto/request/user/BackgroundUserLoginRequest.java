package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("用户登录请求体")
public class BackgroundUserLoginRequest {

    @ApiModelProperty("登录账号")
    @NotBlank(message ="账号不能为空")
    private String loginId;

    @ApiModelProperty("用户密码")
    @NotBlank(message ="密码不能为空")
    private String password;

    @ApiModelProperty("是否开启记住我模式 0-未开启，1-开启")
    private Integer rememberMe;

}
