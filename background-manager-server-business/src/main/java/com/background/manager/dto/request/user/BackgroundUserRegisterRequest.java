package com.background.manager.dto.request.user;

import com.background.manager.constant.RegexConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("用户注册请求体")
public class BackgroundUserRegisterRequest {

    @ApiModelProperty("账号")
    @Pattern(regexp = RegexConstant.Regex.USERNAME_REGEX,message = RegexConstant.Message.USERNAME_NAME)
    @NotBlank(message ="账号不能为空")
    private String loginId;

    @ApiModelProperty("用户密码")
    @Pattern(regexp = RegexConstant.Regex.PASSWORD_REGEX,message = RegexConstant.Message.PASSWORD_NAME)
    @NotBlank(message ="密码不能为空")
    private String password;

    @ApiModelProperty("确认密码")
    @NotBlank(message ="确认密码不能为空")
    private String confirmPassword;
}
