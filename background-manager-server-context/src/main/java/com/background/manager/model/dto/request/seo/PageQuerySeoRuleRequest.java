package com.background.manager.model.dto.request.seo;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("分页查询seo配置规则请求体")
public class PageQuerySeoRuleRequest extends BasePageDTO {
}
