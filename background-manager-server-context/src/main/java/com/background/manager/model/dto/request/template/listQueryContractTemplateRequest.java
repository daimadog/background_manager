package com.background.manager.model.dto.request.template;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("分页查询合同模板请求体")
public class listQueryContractTemplateRequest {

    @ApiModelProperty("合同模板名称")
    private String name;

    @ApiModelProperty("合同模板状态0-不可下载,1-可下载")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    private String creator;
}
