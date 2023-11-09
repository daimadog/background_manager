package com.background.manager.model.dto.request.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("下载文件请求体")
public class DownLoadFileRequest {

    @ApiModelProperty("要下载文件名称")
    private String remoteFileName;

    @ApiModelProperty("要下载文件的地址")
    private String remotePath;

    @ApiModelProperty("本地保存文件路径")
    private String localPath;

    @ApiModelProperty("本地保存文件名称")
    private String localFileName;

}
