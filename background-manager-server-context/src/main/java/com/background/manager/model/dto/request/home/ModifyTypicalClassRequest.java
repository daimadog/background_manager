package com.background.manager.model.dto.request.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("编辑典型案例请求体")
public class ModifyTypicalClassRequest {

    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("主标题")
    private String title;
    @ApiModelProperty("副标题")
    private String subtitle;
    @ApiModelProperty("图片url地址")
    private String imageUrl;
    @ApiModelProperty("内容")
    private String context;
    @ApiModelProperty("外部链接")
    private String connectUrl;

}
