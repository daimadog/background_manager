package com.background.manager.dto.request.user;

import com.background.manager.constant.RegexConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@ApiModel("修改用户信息请求体")
public class ModifyUserInfoRequest {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户头像")
    private String profile;

    @ApiModelProperty("手机号")
    @Pattern(regexp = RegexConstant.Regex.PHONE_REGEX,message= RegexConstant.Message.PHONE_NAME)
    private String phone;

    @ApiModelProperty("邮箱")
    @Pattern(regexp = RegexConstant.Regex.EMAIL_REGEX,message= RegexConstant.Message.EMAIL_NAME)
    private String email;

    @ApiModelProperty("出生日期")
    private Date birthday;

    @ApiModelProperty("性别")
    private String sex;
}
