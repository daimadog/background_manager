package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("通过用户审核请求体")
public class ActivateAccountRequest {

    @ApiModelProperty("用户编号")
    private String userId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("结论")
    private String conclusion;

}
