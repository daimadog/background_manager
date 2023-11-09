package com.background.manager.dto.response.console;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("客户管理列表DTO")
public class CustomerListDTO {

    @TableId(value = "user_id", type = IdType.AUTO)
    private String userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("联系人")
    private String contactName;

    @ApiModelProperty("联系电话")
    private String contactPhone;

    @ApiModelProperty("注册时间")
    private LocalDateTime registerTime;

    @ApiModelProperty("超算平台状态")
    private String status;

    @ApiModelProperty("超算平台现金余额")
    private String balance;

}
