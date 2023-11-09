package com.background.manager.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
@ApiModel("用户信息DTO")
public class BackgroundUserInfoDTO {

   @ApiModelProperty("登录账号")
    private String loginId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户状态")
    private Integer status;

    @ApiModelProperty("用户头像")
    private String profile;

    @ApiModelProperty("用户出生日期")
    private Date birthday;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

}
