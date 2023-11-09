package com.background.manager.model.dto.request.template;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("上传合同模板请求体")
public class CreateTemplateRequest {

    @ApiModelProperty("合同模板名称")
    private String name;

    @ApiModelProperty("模板描述")
    private String description;

    @ApiModelProperty("模板附件地址")
    private String templateUrl;

}
