package com.background.manager.dto.response.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
public class BackgroundSecuritySettingDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("密码长度")
    private Long passwordLength;

    @ApiModelProperty("登录失败次数")
    private Long failNum;

    @ApiModelProperty("锁定时间")
    private Long LockTime;

    @ApiModelProperty("密码规则")
    private List<PasswordRuleDTO> passwordRuleList;

    @ApiModelProperty("常用密码剔除集合")
    List<PasswordEliminationDTO> passwordEliminationList;

}
