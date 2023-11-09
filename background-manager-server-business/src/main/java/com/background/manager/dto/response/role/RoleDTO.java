package com.background.manager.dto.response.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 角色信息DTO
 * @Author: 杜黎明
 * @Date: 2022/09/30 10:46:58
 * @Version: 1.0.0
 */
@Data
@ApiModel("角色信息DTO")
public class RoleDTO {

    @ApiModelProperty("角色ID")
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;


}
