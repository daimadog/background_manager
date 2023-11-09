package com.background.manager.model.dto.response.offlineRegistration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("查询离线登记详情内容")
public class OfflineMessageDTO {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("公司名称/学校名称")
    private String company;

    @ApiModelProperty("职位")
    private String  status;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("处理状态(0-未处理，1-已处理)")
    private Integer  processState;

    @ApiModelProperty("离线登记内容")
    private String messageContent;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
