package com.background.manager.model.dto.request.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel("上传文件请求体")
public class UploadFileRequest {

    @ApiModelProperty("上传的文件")
    private MultipartFile multipartFile;

    @ApiModelProperty("上传文件地址")
    private String dirPath;

}
