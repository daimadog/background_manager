package com.background.manager.dto.request.user;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageQueryTSysUserRequest extends BasePageDTO {

    @ApiModelProperty("关键词搜索")
    private String keyWords;

    @ApiModelProperty("审核状态")
    private Integer applyStatus;
}
