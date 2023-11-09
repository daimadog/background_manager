package com.background.manager.controller;

import com.background.manager.model.dto.request.activity.AddArticleRequest;
import com.background.manager.model.dto.request.news.DownLoadFileRequest;
import com.background.manager.model.dto.request.news.ModifyArticleRequest;
import com.background.manager.model.dto.request.news.PageQueryArticleRequest;
import com.background.manager.model.dto.response.news.ArticleDTO;
import com.background.manager.model.dto.response.news.ArticleDigestDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.model.dto.response.news.ArticleHomeDigestDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

/**
 * @Description: 新闻文章管理控制器
 * @Author: 杜黎明
 * @Date: 2022/10/25 16:52:51
 * @Version: 1.0.0
 */
@RestController
@Api(tags = "新闻文章管理模块")
@RequestMapping("/article")
@Slf4j
public class newsController {

    @Resource
    private BackgroundArticleService backgroundArticleService;

    @PutMapping("/add")
    @ApiOperation(value = "新增新闻文章",notes = "新增新闻文章接口")
    public ApiResponse<String> addArticle(@RequestBody AddArticleRequest request){
        return ApiResponse.ok(backgroundArticleService.add(request));
    }

    @GetMapping("/page")
    @ApiOperation(value = "后台分页查询新闻文章",notes ="后台分页查询新闻文章接口")
    public  ApiResponse<IPage<ArticleDigestDTO>> pageQuery(PageQueryArticleRequest request){
        return ApiResponse.ok(backgroundArticleService.pageQuery(request));
    }

    @GetMapping("/homePage")
    @ApiOperation(value = "门户分页查询新闻文章",notes ="门户分页查询新闻文章接口" )
    public ApiResponse<IPage<ArticleHomeDigestDTO>> pageQueryHomeArticle (PageQueryArticleRequest request){
        return ApiResponse.ok(backgroundArticleService.pageQueryHomeArticle(request));
    }

    @PutMapping("/modify")
    @ApiOperation(value = "编辑新闻文章",notes = "编辑新闻文章接口")
    public ApiResponse<Void> modifyArticle(@RequestBody ModifyArticleRequest request){
        backgroundArticleService.modify(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除新闻文章",notes = "删除新闻文章接口")
    public  ApiResponse<Void> delete(@ApiParam("新闻文章编号") @PathVariable ("id") Long id){
        if (backgroundArticleService.delete(id)){
            return ApiResponse.ok();
        }
            return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
    }

    @GetMapping("/getArticle/{id}")
    @ApiOperation(value = "查看新闻详情",notes = "查看新闻详情接口")
    public  ApiResponse<ArticleDTO> queryArticle(@ApiParam("新闻文章编号") @PathVariable ("id") Long id){
        return ApiResponse.ok(backgroundArticleService.getArticle(id));
    }

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件",notes = "上传文件接口")
    @ApiImplicitParam(name = "files", value = "上传的文件", dataTypeClass = MultipartFile.class
                        , required = true,allowMultiple = true,paramType = "query")
    public ApiResponse<String> uploadImage(@RequestParam("file")MultipartFile  multipartFiles){
        return ApiResponse.ok(backgroundArticleService.upload(multipartFiles));
    }

    @PutMapping("/download")
    @ApiOperation("下载文件")
    public ApiResponse<Boolean> download (@RequestBody DownLoadFileRequest request){
        backgroundArticleService.download(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/deleteImage")
    @ApiOperation(value = "删除文件",notes = "删除文件接口")
    public ApiResponse <Void> deleteImage(@RequestParam("imageUrl") String imageUrl){
        backgroundArticleService.deleteImage(imageUrl);
        return ApiResponse.ok();
    }

}