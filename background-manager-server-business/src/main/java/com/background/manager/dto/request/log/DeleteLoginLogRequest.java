package com.background.manager.dto.request.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("删除登录日志请求体")
public class DeleteLoginLogRequest {

    @ApiModelProperty("删除的登录日志id集合")
    private List<Long> idList;

}
