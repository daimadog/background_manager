package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("完善用户信息请求体")
public class ImproveUserInfoRequest{

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
}
