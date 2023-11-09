package com.background.manager.model.dto.request.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("新增合作伙伴请求体")
public class AddPartnersRequest {

    @ApiModelProperty("标题介绍")
    private String title;
    @ApiModelProperty("图片url地址")
    private String imageUrl;
}
