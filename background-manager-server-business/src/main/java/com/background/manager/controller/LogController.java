package com.background.manager.controller;

import com.background.manager.dto.request.log.DeleteLoginLogRequest;
import com.background.manager.dto.request.log.DeleteOperationLogRequest;
import com.background.manager.dto.request.log.PageQueryLoginLogRequest;
import com.background.manager.dto.request.log.PageQueryOperationLogRequest;
import com.background.manager.dto.response.log.LoginLogDigestDTO;
import com.background.manager.dto.response.log.OperationLogDigestDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundLoginLogService;
import com.background.manager.service.BackgroundOperationLogService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Description: 日志控制器
 * @Author: 杜黎明
 * @Date: 2022/10/13 17:58:44
 * @Version: 1.0.0
 */
@Api(tags = "日志管理")
@RestController("/log")
public class LogController {

    @Resource
    private BackgroundLoginLogService backgroundLoginLogService;
    @Resource
    private BackgroundOperationLogService backgroundOperationLogService;

    @GetMapping("/pageQueryLoginLog")
    @ApiOperation(value = "分页查询登录日志",notes ="分页查询登录日志接口")
    @DS("salve")
    public ApiResponse<IPage<LoginLogDigestDTO>> pageQueryLoginLog(PageQueryLoginLogRequest request){
        return ApiResponse.ok(backgroundLoginLogService.pageQuery(request));
    }

    @PutMapping("/removeLoginLog")
    @ApiOperation(value = "批量删除登录日志",notes = "批量删除登录日志接口")
    @DS("salve")
    public  ApiResponse<Void> deleteLoginLog(@RequestBody DeleteLoginLogRequest request){
        if (backgroundLoginLogService.delete(request)){
            return ApiResponse.ok();
        }
            return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
    }

    @DeleteMapping("/clearLoginLog")
    @ApiOperation(value ="清空登录日志",notes ="清空登录日志")
    @DS("salve")
    public ApiResponse<Void> clearLoginLog(){
        if (backgroundLoginLogService.clean()){
            return ApiResponse.ok();
        }
            return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
    }

    @GetMapping("/pageQueryOperationLog")
    @ApiOperation(value ="分页查询操作日志",notes = "分页查询操作日志接口")
    public  ApiResponse<IPage<OperationLogDigestDTO>> pageQueryOperationLog(PageQueryOperationLogRequest request){
        return ApiResponse.ok(backgroundOperationLogService.pageQuery(request));
    }

    @PutMapping("/removeOperationLog")
    @ApiOperation(value = "批量删除操作日志",notes = "批量删除操作日志接口")
    public  ApiResponse<Void> deleteOperationLog(@RequestBody DeleteOperationLogRequest request){
        if (backgroundOperationLogService.delete(request)){
            return ApiResponse.ok();
        }
        return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
    }

    @DeleteMapping("/clearOperationLog")
    @ApiOperation(value = "清空操作日志",notes = "清空操作日志接口")
    public ApiResponse<Void> clearOperationLog(){
        if (backgroundOperationLogService.clean()){
            return ApiResponse.ok();
        }
        return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
    }

}