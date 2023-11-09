package com.background.manager.model.dto.request.seo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("新增seo配置请求体")
public class AddSeoRuleRequest {

    @ApiModelProperty("Seo配置规则")
    private String seoRule;
}
