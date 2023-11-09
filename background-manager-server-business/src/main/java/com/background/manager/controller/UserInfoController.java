package com.background.manager.controller;

import com.background.manager.dto.request.user.ModifyUserInfoRequest;
import com.background.manager.dto.request.user.ModifyUserPasswordRequest;
import com.background.manager.dto.response.user.UserInfoDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundUserInfoService;
import com.background.manager.service.BackgroundUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Description: 后台用户个人信息控制器
 * @Author: 杜黎明
 * @Date: 2022/10/09 17:33:39
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/userInfo")
@Api(tags = "用户个人信息管理")
public class UserInfoController {

    @Resource
    private BackgroundUserInfoService backgroundUserInfoService;
    @Resource
    private BackgroundUserService backgroundUserService;

    @GetMapping("/query/{id}")
    @ApiOperation(value = "运营用户信息详情",notes = "个人信息详情接口")
    public ApiResponse<UserInfoDTO> query(@ApiParam("用户id") @PathVariable("id") Long id){
        return ApiResponse.ok( backgroundUserInfoService.findUserInfoById(id));
    }

    @PutMapping("/modifyPassword")
    @ApiOperation(value = "修改运营用户密码",notes = "修改用户密码")
    public ApiResponse<Void> modifyPassword(@RequestBody ModifyUserPasswordRequest request){
        if (backgroundUserService.modifyPassword(request)){
            return ApiResponse.ok();
        }
            return ApiResponse.error(ResultCodeEnum.UPDATE_FAIL);
    }

    @PostMapping("/modify")
    @ApiOperation(value = "完善运营用户信息",notes = "完善用户个人信息接口")
    public ApiResponse<Void> modify(@RequestBody ModifyUserInfoRequest request){
        if (backgroundUserInfoService.modify(request)){
            return ApiResponse.ok();
        }
        return ApiResponse.error(ResultCodeEnum.UPDATE_FAIL);
    }



}
