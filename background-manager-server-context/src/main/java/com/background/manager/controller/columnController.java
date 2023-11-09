package com.background.manager.controller;

import com.background.manager.model.dto.request.column.AddColumnRequest;
import com.background.manager.model.dto.request.column.ListQueryColumnRequest;
import com.background.manager.model.dto.request.column.ModifyColumnRequest;
import com.background.manager.model.dto.response.column.ColumnDigestDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 栏目管理控制器
 * @Author: 杜黎明
 * @Date: 2022/10/26 10:31:43
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/column")
@Api(tags = "栏目管理模块")
public class columnController {

    @Resource
    private BackgroundColumnService backgroundColumnService;

    @GetMapping("/list")
    @ApiOperation(value = "树形列表查询栏目",notes = "树形列表查询栏目接口")
    public ApiResponse<List<ColumnDigestDTO>> listQuery(ListQueryColumnRequest request){
        return ApiResponse.ok(backgroundColumnService.listQuery(request));
    }

    @PutMapping("/add")
    @ApiOperation(value = "新增栏目",notes ="新增栏目接口")
    public  ApiResponse<String> add(@RequestBody AddColumnRequest request){
        return ApiResponse.ok(backgroundColumnService.add(request));
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改栏目",notes = "修改栏目接口")
    public  ApiResponse<Void> modify(@RequestBody ModifyColumnRequest request){
        backgroundColumnService.modify(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除栏目",notes = "删除栏目接口")
    public ApiResponse<Void> delete (@ApiParam("栏目编号") @PathVariable ("id") Long id){
        backgroundColumnService.delete(id);
        return ApiResponse.ok();
    }

    @PostMapping("/moveUpColumn/{id}")
    @ApiOperation("栏目菜单向上移动")
    public ApiResponse<Void> moveUpColumn(@ApiParam("栏目编号") @PathVariable ("id") Long id){
        backgroundColumnService.moveUpColumn(id);
        return ApiResponse.ok();
    }

    @PostMapping("/moveDownColumn/{id}")
    @ApiOperation("栏目菜单向下移动")
    public ApiResponse<Void> moveDownColumn(@ApiParam("栏目编号") @PathVariable ("id") Long id){
        backgroundColumnService.moveDownColumn(id);
        return ApiResponse.ok();
    }

}
