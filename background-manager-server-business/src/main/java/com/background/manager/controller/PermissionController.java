package com.background.manager.controller;

import com.background.manager.dto.request.permission.AddPermsRequest;
import com.background.manager.dto.request.permission.ModifyPermsRequest;
import com.background.manager.dto.request.permission.QueryPermsRequest;
import com.background.manager.dto.response.permission.PermissionDigestDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundMenuService;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户权限控制器
 * @Author: 杜黎明
 * @Date: 2022/10/09 23:59:19
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/perms")
@Api(tags = "用户权限管理")
@DS("master")
public class PermissionController {

    @Resource
    private BackgroundMenuService backgroundMenuService;

    @ApiOperation(value = "查询权限菜单列表",notes ="查询权限菜单列表接口" )
    @GetMapping("/pageQuery")
    public ApiResponse<List<PermissionDigestDTO>> listQuery(QueryPermsRequest request){
        return ApiResponse.ok(backgroundMenuService.listQuery(request));
    }

    @PostMapping("/add")
    @ApiOperation(value="新增权限菜单",notes="新增权限菜单接口")
    public ApiResponse<String> add(@RequestBody AddPermsRequest request){
        return ApiResponse.ok(backgroundMenuService.add(request));
    }

    @PutMapping("/modify")
    @ApiOperation(value="修改权限菜单",notes = "修改权限菜单接口")
    public ApiResponse<Void> modify(@RequestBody ModifyPermsRequest request){
        if (backgroundMenuService.modify(request)){
            return ApiResponse.ok();
        }
            return ApiResponse.error(ResultCodeEnum.UPDATE_FAIL);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除权限菜单",notes ="删除权限菜单接口")
    public ApiResponse<Void> delete(@ApiParam("权限id") @PathVariable("id") Long id){
        if (backgroundMenuService.delete(id)){
            return ApiResponse.ok();
        }
            return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
    }

}
