package com.background.manager.dto.request.log;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiOperation("分页查询操作日志请求体")
public class PageQueryOperationLogRequest extends BasePageDTO {

    @ApiModelProperty("搜索关键词")
    private String keyWord;

    @ApiModelProperty("操作日志状态0-成功,1-失败")
    private Integer requestStatus;

    @ApiModelProperty("操作开始时间")
    private String startTime;

    @ApiModelProperty("操作结束时间")
    private String endTime;

}
