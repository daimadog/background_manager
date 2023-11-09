package com.background.manager.controller;

import com.background.manager.dto.request.role.AssignRoleRequest;
import com.background.manager.dto.request.user.AddUserRequest;
import com.background.manager.dto.request.user.ModifyUserRequest;
import com.background.manager.dto.request.user.PageQueryUserInfoRequest;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.user.UserInfoDigestDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundUserInfoService;
import com.background.manager.service.BackgroundUserRoleService;
import com.background.manager.service.BackgroundUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 后台用户管理控制器
 * @Author: 杜黎明
 * @Date: 2022/10/09 17:33:20
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "后台用户管理")
public class UserController {

    @Resource
    private BackgroundUserInfoService backgroundUserInfoService;
    @Resource
    private BackgroundUserService backgroundUserService;
    @Resource
    private BackgroundUserRoleService backgroundUserRoleService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询运营用户列表",notes = "分页查询用户列表接口")
    public ApiResponse<IPage<UserInfoDigestDTO>> pageQuery (PageQueryUserInfoRequest request){
        return ApiResponse.ok(backgroundUserInfoService.pageQuery(request));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除运营用户",notes = "删除用户接口")
    public ApiResponse<Void> delete(@ApiParam("用户编号") @PathVariable("id") Long id){
        if (backgroundUserService.delete(id)){
            return ApiResponse.ok();
        }
            return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
    }

    @PutMapping("/freeze/{id}")
    @ApiOperation(value = "冻结运营用户",notes = "冻结用户接口")
    public ApiResponse<Void> freeze(@ApiParam("用户编号") @PathVariable("id") Long id){
        if (backgroundUserService.freeze(id)){
            return ApiResponse.ok();
        }
        return ApiResponse.error(ResultCodeEnum.UPDATE_FAIL);
    }

    @PutMapping("/unFreeze/{id}")
    @ApiOperation(value="解冻运营用户",notes="解冻用户接口")
    public ApiResponse<Void> unFreeze(@ApiParam("用户编号") @PathVariable("id") Long id){
        if (backgroundUserService.unFreeze(id)){
            return ApiResponse.ok();
        }
            return ApiResponse.error(ResultCodeEnum.UPDATE_FAIL);
    }

    @PutMapping("/add")
    @ApiOperation(value = "添加运营用户",notes = "添加运营端用户接口")
    public ApiResponse<String> addUser(@Valid  @RequestBody AddUserRequest request){
        return ApiResponse.ok(backgroundUserService.add(request));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改运营用户信息",notes = "修改运营端用户信息接口")
    public ApiResponse<Void> editUser(@RequestBody ModifyUserRequest request){
        backgroundUserService.edit(request);
        return ApiResponse.ok();
    }

    @GetMapping("/queryRole/{id}")
    @ApiOperation(value = "查看当前用户角色",notes = "查看当前用户角色接口")
    public ApiResponse<List<RoleDTO>> queryUserRole(@ApiParam("用户编号") @PathVariable("id") Long id){
        return ApiResponse.ok(backgroundUserService.getRoles(id));
    }

    @PutMapping("/assign")
    @ApiOperation(value="设置角色",notes = "设置角色接口")
    public  ApiResponse<Void> assignRole(@RequestBody AssignRoleRequest request){
        backgroundUserService.assignRole(request.getUserId(),request.getRoleId());
        return ApiResponse.ok();
    }


}