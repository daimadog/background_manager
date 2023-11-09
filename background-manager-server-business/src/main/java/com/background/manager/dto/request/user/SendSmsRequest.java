package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("发送审核通过短信验证码请求体")
public class SendSmsRequest {

    @ApiModelProperty(value = "手机号码",required = true)
    private String contactPhone;

}
