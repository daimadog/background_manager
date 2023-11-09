package com.background.manager.model.dto.request.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("删除文件请求体")
public class DeleteFileRequest {

    @ApiModelProperty("图片路径地址")
    private String imageUrl;

}
