package com.background.manager.dto.response.role;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiOperation("角色分页查询DTO")
public class RoleDigestDTO {

    @ApiModelProperty("角色Id")
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色英文名称")
    private String enName;

    @ApiModelProperty("角色状态")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
