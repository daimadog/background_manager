package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Max;
import java.util.List;

@Data
@ApiModel("修改密码安全设置请求体")
public class ModifyBackgroundSecuritySettingRequest {

    @ApiModelProperty("主键ID")
    private Long id;

    @Max(value = 32)
    @ApiModelProperty("密码长度")
    private Long passwordLength;

    @ApiModelProperty("登录失败次数")
    private Long failNum;

    @ApiModelProperty("锁定时间")
    private Long LockTime;

    @ApiModelProperty("密码规则id集合")
    private List<Integer> ruleList;

    @ApiModelProperty("常用密码剔除集合")
    private List<String> passwordList;

}


