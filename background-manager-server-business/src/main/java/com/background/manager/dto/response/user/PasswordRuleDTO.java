package com.background.manager.dto.response.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PasswordRuleDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("规则描述")
    private String description;

}
