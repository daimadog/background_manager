package com.background.manager.dto.request.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("批量删除操作日志请求体")
public class DeleteOperationLogRequest {

    @ApiModelProperty("删除的操作日志id集合")
    private List<Long> idList;
}
