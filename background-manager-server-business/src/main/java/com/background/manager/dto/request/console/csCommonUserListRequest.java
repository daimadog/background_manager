package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("管理员查看普通用户列表请求体")
public class csCommonUserListRequest {

    @ApiModelProperty(value = "页码，默认值为1",required = true)
    private int page;
    @ApiModelProperty(value = "页大小", required = true)
    private int size;

    @ApiModelProperty(value = "用户角色:Member-组成员,Leader_组管理员")
    private String roleId;

    @ApiModelProperty("中间以英文逗号隔开，可查询多个平台用户名")
    private  List<String> userName;

    @ApiModelProperty("中间以英文逗号隔开，可查询多个用户姓名")
    private List<String> fullNames;

    @ApiModelProperty("用户类别:agent-代理商客户,none-无类别")
    private String userType;

    @ApiModelProperty("电话号码")
    private String mobilePhoneNum;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("最后一次登录时间开始")
    private String lastLoginTimeStart;

    @ApiModelProperty("最后一次登录时间结束")
    private String lastLoginTimeEnd;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("账户类型:Offical-正式账户，Trial-试用账户")
    private String accountType;

}
