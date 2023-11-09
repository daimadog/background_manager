package com.background.manager.controller;

import com.background.manager.dto.request.role.AddRoleRequest;
import com.background.manager.dto.request.role.AssignPermsRequest;
import com.background.manager.dto.request.role.ModifyRoleRequest;
import com.background.manager.dto.request.role.PageQueryRoleRequest;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.role.RoleDetailDTO;
import com.background.manager.dto.response.role.RoleDigestDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundRoleMenuService;
import com.background.manager.service.BackgroundRoleService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 后台角色管理控制器
 * @Author: 杜黎明
 * @Date: 2022/10/09 22:36:12
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
@DS("master")
public class RoleController {

    @Resource
    private BackgroundRoleService backgroundRoleService;
    @Resource
    private BackgroundRoleMenuService backgroundRoleMenuService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询角色列表",notes = "分页查询角色列表接口")
    public ApiResponse<IPage<RoleDigestDTO>> pageQuery (PageQueryRoleRequest request){
        return ApiResponse.ok(backgroundRoleService.pageQuery(request));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增角色",notes = "新增角色接口")
    public ApiResponse<String> add (@RequestBody AddRoleRequest request){
        return ApiResponse.ok(backgroundRoleService.add(request));
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改角色信息",notes = "修改角色信息接口")
    public  ApiResponse<Void> modify(@RequestBody ModifyRoleRequest request){
        backgroundRoleService.modify(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除指定角色",notes = "删除角色接口")
    public ApiResponse<Void> delete(@ApiParam("角色id") @PathVariable("id") Long id){
        if (backgroundRoleService.delete(id)){
            return ApiResponse.ok();
        }else {
            return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
        }
    }

    @GetMapping("/queryRole/{id}")
    @ApiOperation(value = "查询当前角色详情",notes = "查询角色详情接口")
    public ApiResponse<RoleDetailDTO> queryRole(@ApiParam("角色id") @PathVariable("id") Long id){
        return ApiResponse.ok(backgroundRoleService.getRoleDetail(id));
    }

    @GetMapping("/queryPerms/{id}")
    @ApiOperation(value = "查询当前角色权限",notes = "查询角色权限接口")
    public  ApiResponse<List<PermissionDTO>> queryPerms (@ApiParam("角色id") @PathVariable("id") Long id){
        return  ApiResponse.ok(backgroundRoleMenuService.getPerms(id));
    }

    @PutMapping("/assignPerms")
    @ApiOperation(value = "角色分配权限",notes = "角色分配权限接口")
    public ApiResponse<Void> assignPerms (@RequestBody AssignPermsRequest request){
        backgroundRoleService.assignPerms(request);
        return ApiResponse.ok();
    }

    @GetMapping("/queryAllRoles")
    @ApiOperation(value = "查询角色列表",notes = "查询角色列表接口")
    public ApiResponse<List<RoleDTO>> queryAllRole(){
        return ApiResponse.ok(backgroundRoleService.queryAllRole());
    }
}
