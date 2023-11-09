package com.background.manager.model.dto.request.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("下载文件本地请求体")
public class DownLoadRequest {

    @ApiModelProperty("下载文件地址")
    private String downloadUrl;

    @ApiModelProperty("本地保存文件路径")
    private String localPath;

    @ApiModelProperty("本地保存文件名称")
    private String localFileName;

}
