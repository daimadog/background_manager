package com.background.manager.model.dto.response.seo;

import com.background.manager.model.ContextBasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("列表查询seo配置")
public class BackgroundSeoRuleDTO extends ContextBasePO {


    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("seo配置规则")
    private String seoRule;

}
