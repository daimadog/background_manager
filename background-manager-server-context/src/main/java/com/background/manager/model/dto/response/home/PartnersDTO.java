package com.background.manager.model.dto.response.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("合作伙伴DTP")
public class PartnersDTO {

    @ApiModelProperty("图片url地址")
    private String imageUrl;

}
