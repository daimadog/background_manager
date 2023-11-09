package com.background.manager.controller;

import com.background.manager.model.dto.request.announcement.AddAnnouncementRequest;
import com.background.manager.model.dto.request.announcement.ModifyAnnouncementRequest;
import com.background.manager.model.dto.request.announcement.PageQueryAnnouncementRequest;
import com.background.manager.model.dto.response.announcement.BackgroundAnnouncementDigestDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundAnnouncementService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/announce")
@Api(tags = "公告管理模块")
@AllArgsConstructor
@DS("salve")
public class announceController {

    private final BackgroundAnnouncementService backgroundAnnouncementService;

    @ApiOperation(value = "发布公告",notes = "发布公告接口")
    @PutMapping("/add")
    public ApiResponse<String> add(@Valid  @RequestBody AddAnnouncementRequest request){
        return ApiResponse.ok(backgroundAnnouncementService.add(request));
    }

    @ApiOperation(value = "分页查询公告",notes = "分页查询公告接口")
    @GetMapping("/pageQuery")
    public ApiResponse<IPage<BackgroundAnnouncementDigestDTO>> pageQueryAnnounce(PageQueryAnnouncementRequest request){
        return ApiResponse.ok(backgroundAnnouncementService.pageQuery(request));
    }

    @ApiOperation(value = "编辑公告(发布/取消发布)",notes = "编辑公告(发布/取消发布)接口")
    @PostMapping("/modify")
    public ApiResponse<Void> modify (@RequestBody ModifyAnnouncementRequest request){
        backgroundAnnouncementService.edit(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "删除公告",notes = "删除公告接口")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@ApiParam("公告id") @PathVariable Long id){
        backgroundAnnouncementService.delete(id);
        return ApiResponse.ok();
    }

}
