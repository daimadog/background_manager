package com.background.manager.dto.response.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("登录日志DTO")
public class LoginLogDigestDTO {

    @ApiModelProperty("日志编号")
    @TableId(value = "id",type = IdType.AUTO)
    private  Long id;

    @ApiModelProperty("登录账号")
    private String loginId;

    @ApiModelProperty("登录用户名")
    private String username;

    @ApiModelProperty("登录状态")
    private Integer status;

    @ApiModelProperty("登录Ip地址")
    private String creatorIp;

    @ApiModelProperty("登录地点")
    private String creatorAddress;

    @ApiModelProperty("登录日志消息")
    private String message;

    @ApiModelProperty("登录时间")
    private LocalDateTime createTime;

    @ApiModelProperty("日志标签(0-运营,1-用户)")
    private Integer logFlag;


}
