package com.background.manager.controller;

import com.background.manager.model.dto.request.activity.AddActivityRequest;
import com.background.manager.model.dto.request.activity.ModifyActivityRequest;
import com.background.manager.model.dto.request.activity.PageQueryActivityRequest;
import com.background.manager.model.dto.response.activity.ActivityDTO;
import com.background.manager.model.dto.response.activity.ActivityDigestDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundActivityService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Description: 活动管理控制器
 * @Author: 杜黎明
 * @Date: 2022/10/26 19:30:08
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/activity")
@Api(tags = "活动管理模块")
public class activityController {

    @Resource
    private BackgroundActivityService backgroundActivityService;

    @GetMapping("/pageQuery")
    @ApiOperation(value = "分页查询活动列表",notes = "分页展示活动列表接口")
    public ApiResponse<IPage<ActivityDigestDTO>> pageQuery(PageQueryActivityRequest request){
        return ApiResponse.ok(backgroundActivityService.pageQuery(request));
    }

    @PutMapping("/add")
    @ApiOperation(value ="新增活动",notes = "新增活动接口")
    public ApiResponse<String> add(@RequestBody AddActivityRequest request){
        return ApiResponse.ok(backgroundActivityService.add(request));
    }

    @PutMapping("/modify")
    @ApiOperation(value = "编辑活动",notes ="编辑活动接口")
    public  ApiResponse<Void> modify(@RequestBody ModifyActivityRequest request){
        backgroundActivityService.modify(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除活动",notes = "删除活动接口")
    public ApiResponse<Void> delete(@ApiParam("活动id编号") @PathVariable("id") Long id){
        backgroundActivityService.delete(id);
        return ApiResponse.ok();
    }

    @GetMapping("/getActivity/{id}")
    @ApiOperation(value = "查看活动详情",notes = "查看活动详情接口")
    public ApiResponse<ActivityDTO> getActivity(@ApiParam("活动id编号") @PathVariable("id") Long id){
        return ApiResponse.ok(backgroundActivityService.getActivity(id));
    }

}