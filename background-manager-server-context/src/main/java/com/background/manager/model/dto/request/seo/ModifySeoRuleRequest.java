package com.background.manager.model.dto.request.seo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改seo配置请求体")
public class ModifySeoRuleRequest {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("Seo配置规则")
    private String seoRule;
}
