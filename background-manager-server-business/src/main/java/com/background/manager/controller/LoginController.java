package com.background.manager.controller;

import com.background.manager.dto.request.user.BackgroundUserLoginRequest;
import com.background.manager.dto.request.user.BackgroundUserRegisterRequest;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Description: 用户登录注册注销控制器
 * @Author: 杜黎明
 * @Date: 2022/10/08 14:24:47
 * @Version: 1.0.0
 */
@RestController
@Api(tags=
        "后台登录注册管理"
    )
public class LoginController {

    @Resource
    private BackgroundUserService backgroundUserService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册接口")
    public ApiResponse<String> register(@Valid @RequestBody BackgroundUserRegisterRequest request){
        String loginId = backgroundUserService.register(request);
        if (StringUtils.isNotBlank(loginId)){
            return ApiResponse.ok(loginId);
        }
            return ApiResponse.error(ResultCodeEnum.REGISTER_FAIL);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录接口")
    public  ApiResponse<LoginUserInfoDTO> login(@RequestBody BackgroundUserLoginRequest request, HttpServletRequest httpServletRequest){
        return ApiResponse.ok(backgroundUserService.login(request,httpServletRequest));
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户注销",notes = "用户注销接口")
    public ApiResponse<Void> logout(){
        backgroundUserService.logout();
        return ApiResponse.ok();
    }

    @GetMapping("/checkLogin")
    @ApiOperation(value = "校验登录",notes = "校验登录接口")
    public ApiResponse<Boolean> checkLogin(){
        return ApiResponse.ok(backgroundUserService.checkLogin());
    }


}
