package com.background.manager.dto.request.user;

import com.background.manager.constant.RegexConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("编辑运营端用户请求体")
public class ModifyUserRequest {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("用户登录账号")
    @Pattern(regexp = RegexConstant.Regex.USERNAME_REGEX,message = RegexConstant.Message.USERNAME_NAME)
    private String loginId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户邮箱")
    @Pattern(regexp = RegexConstant.Regex.EMAIL_REGEX,message= RegexConstant.Message.EMAIL_NAME)
    private String email;

    @ApiModelProperty("角色id")
    private List<Long> roleId;

}
