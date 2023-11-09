package com.background.manager.dto.response.user;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
@ApiModel("登录成功后返回的用户信息")
public class LoginUserInfoDTO {
    @ApiModelProperty("登录账号id")
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户头像")
    private String profile;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("出生日期")
    private Date birthday;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("用户状态")
    private Integer status;

    @ApiModelProperty("管理员标识(0-普通运营人员，1-管理员")
    private Integer administratorFlag;

    @ApiModelProperty("token信息")
    private SaTokenInfo tokenInfo;
}
