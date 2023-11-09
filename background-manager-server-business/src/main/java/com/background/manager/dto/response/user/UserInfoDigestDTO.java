package com.background.manager.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户分页查询返回结果")
public class UserInfoDigestDTO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("登录账号id")
    private String loginId;

    @ApiModelProperty("用户昵称")
    private String username;

    @ApiModelProperty("头像")
    private String profile;

    @ApiModelProperty("出生日期")
    private Date birthday;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("管理员标识(0-普通运营人员，1-管理员")
    private Integer administratorFlag;

    @ApiModelProperty("角色权限")
    private List<String> roleName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime modifyTime;
}
