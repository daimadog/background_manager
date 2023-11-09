package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取客户资源列表请求体")
public class productResourcesListRequest extends PageRequest {

    @ApiModelProperty(value = "产品类型（OBS:对象存储OBS" + "ModelArts:昇腾Modelarts共享资源池" +
              "DRP:昇腾Modelarts专属资源池）")
    private String productType;

    @ApiModelProperty(value = "父级组织id")
    private Long parentOrgSid;

    @ApiModelProperty(value = "创建时间",required = false)
    private String createDt;


}
