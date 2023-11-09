package com.background.manager.dto.request.user;

import com.background.manager.constant.RegexConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("添加运营端用户接口")
public class AddUserRequest {

    @ApiModelProperty("用户登录账号")
    @Pattern(regexp = RegexConstant.Regex.USERNAME_REGEX,message = RegexConstant.Message.USERNAME_NAME)
    private String loginId;

    @ApiModelProperty("用户登录密码")
    @Pattern(regexp = RegexConstant.Regex.PASSWORD_REGEX,message = RegexConstant.Message.PASSWORD_NAME)
    private String password;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户头像")
    private String profile;

    @ApiModelProperty("用户出生日期")
    private Date birthday;

    @ApiModelProperty("用户性别")
    private String sex;

    @ApiModelProperty("用户手机号")
    @Pattern(regexp = RegexConstant.Regex.PHONE_REGEX,message= RegexConstant.Message.PHONE_NAME)
    private String phone;

    @ApiModelProperty("用户邮箱")
    @Pattern(regexp = RegexConstant.Regex.EMAIL_REGEX,message= RegexConstant.Message.EMAIL_NAME)
    private String email;

    @ApiModelProperty("角色id")
    private List<Long> roleId;

}
