package com.background.manager.dto.request.user;

import com.background.manager.constant.RegexConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@ApiModel("修改密码请求体")
public class ModifyUserPasswordRequest {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("旧密码")
    private String oldPassword;

    @ApiModelProperty("新密码")
    private String newPassword;

    @ApiModelProperty("确认新密码")
    @Pattern(regexp = RegexConstant.Regex.PASSWORD_REGEX,message = RegexConstant.Message.PASSWORD_NAME)
    private String confirmNewPassword;

}
