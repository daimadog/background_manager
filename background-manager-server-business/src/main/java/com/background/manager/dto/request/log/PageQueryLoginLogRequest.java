package com.background.manager.dto.request.log;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询登录日志请求体")
public class PageQueryLoginLogRequest extends BasePageDTO {

    @ApiModelProperty("关键词搜索")
    private String keyWord;

    @ApiModelProperty("登录状态")
    private Integer status;

    @ApiModelProperty("登录开始时间")
    private String startTime;

    @ApiModelProperty("登录结束时间")
    private String endTime;

    @ApiModelProperty("日志标签(0-运营,1-用户)")
    private Integer logFlag;

}
