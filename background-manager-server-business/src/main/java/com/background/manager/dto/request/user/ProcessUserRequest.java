package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("审核用户请求体")
public class ProcessUserRequest {

    @ApiModelProperty("用户编号")
    private String userId;

    @ApiModelProperty("备注")
    private String remark;

}
