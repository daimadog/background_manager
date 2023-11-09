package com.background.manager.dto.request.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("超算获取用户账单列表")
public class CsBillListRequest {

    @ApiModelProperty("账户名称")
    private String accountName;
    @ApiModelProperty("区域id")
    private String clusterId;
    @ApiModelProperty(value = "页码，默认值为1",required = true)
    private int pageNum;
    @ApiModelProperty(value = "页大小", required = true)
    private int pageSize;
    @ApiModelProperty(value = "开始时间查询，格式2022-12-01 00:00:00",required = true)
    private String startTime;
    @ApiModelProperty(value = "结束时间查询，格式2022-12-01 00:00:00",required = true)
    private String endTime;

}
