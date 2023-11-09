package com.background.manager.model.dto.request.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@ApiModel("获取KitToken请求体")
public class GetKitTokenRequest {

    @ApiModelProperty("appId")
    private String appId;
    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("时间")
    private Integer time;
    @ApiModelProperty("nonce")
    private  String nonce;


}
