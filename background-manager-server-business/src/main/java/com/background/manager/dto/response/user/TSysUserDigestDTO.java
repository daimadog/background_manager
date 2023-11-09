package com.background.manager.dto.response.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TSysUserDigestDTO {

    @ApiModelProperty("用户编号")
    private String userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("联系人")
    private String contactName;

    @ApiModelProperty("联系电话")
    private String contactPhone;

    @ApiModelProperty("联系邮箱")
    private String contactEmail;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLogin;

    @ApiModelProperty("注册时间")
    private LocalDateTime registerTime;

    @ApiModelProperty("状态")
    private Integer applyStatus;

    @ApiModelProperty("是否存在资源申请表0-没有，1-有")
    private Integer existResourceApplicantForm;

}
