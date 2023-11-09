package com.background.manager.controller;

import com.background.manager.model.dto.request.news.DownLoadRequest;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Api(tags = "文件管理模块")
@AllArgsConstructor
public class fileController {

    private final FileService fileService;

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件",notes = "上传文件接口")
    @ApiImplicitParam(name = "files", value = "上传的文件", dataTypeClass = MultipartFile.class
            , required = true,allowMultiple = true,paramType = "query")
    public ApiResponse<String> upload(@RequestParam("file")MultipartFile multipartFiles,@ApiParam("上传文件地址") @RequestParam("url")String  dirPath){
        return ApiResponse.ok(fileService.upload(multipartFiles,dirPath));
    }

    @PutMapping("/download")
    @ApiOperation("下载文件")
    public ApiResponse<Boolean> download(@RequestBody DownLoadRequest request){
        return ApiResponse.ok(fileService.download(request));
    }

    @DeleteMapping("/deleteFile")
    @ApiOperation("删除文件")
    public ApiResponse<Boolean> delete(@RequestParam("imageUrl") String imageUrl){
        return ApiResponse.ok(fileService.delete(imageUrl));
    }

}
