package com.background.manager.controller;

import com.background.manager.cache.lock.annotation.Repeat;
import com.background.manager.dto.request.user.*;
import com.background.manager.dto.response.user.TSysUserDigestDTO;
import com.background.manager.dto.response.user.UserEnterpriseCertificationDTO;
import com.background.manager.dto.response.user.UserLoginConfigurationDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.*;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 前台门户系统用户管理控制器
 * @Author: 杜黎明
 * @Date: 2022/11/03 17:54:00
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/receptionUser")
@Api(tags = "前台门户系统用户管理")
@DS("salve")
public class ReceptionUserController {

    @Resource
    private TSysUserService tSysUserService;
    @Resource
    private BackgroundSecuritySettingService backgroundSecuritySettingService;
    @Resource
    private PasswordRuleService passwordRuleService;
    @Resource
    private ResourceApplicationFormService resourceApplicationFormService;
    @Resource
    private UserEnterpriseCertificationService userEnterpriseCertificationService;
    @Resource
    private UserLoginConfigurationService userLoginConfigurationService;

    @GetMapping("/page")
    @ApiOperation(value="分页查询前台用户",notes = "分页查询前台用户接口")
    public ApiResponse<IPage<TSysUserDigestDTO>> pageQuery (PageQueryTSysUserRequest request){
        return ApiResponse.ok(tSysUserService.pageQuery(request));
    }

    @PostMapping("/listReceptionUser")
    @ApiOperation(value = "列表查询前台用户",notes = "列表查询前台用户接口")
    public ApiResponse<List<TSysUserDigestDTO>> listReceptionUser(@RequestBody ListReceptionUserRequest request){
        return ApiResponse.ok(tSysUserService.listReceptionUser(request));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除前台用户",notes = "删除前台用户接口")
    public ApiResponse<Void> delete(@RequestBody DeleteUserRequest request){
        if (tSysUserService.delete(request)){
            return ApiResponse.ok();
        }
        return ApiResponse.error(ResultCodeEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "查看资源申请表详情",notes ="查看资源申请表详情")
    @GetMapping("/getResourceApplicationDetail/{userId}")
    public ApiResponse<ResourceApplicationFormDTO> getResourceApplicationForm(@ApiParam("用户编号") @PathVariable String userId ){
        return ApiResponse.ok(resourceApplicationFormService.getResourceApplicationForm(userId));
    }

    @ApiOperation(value = "通过用户审核",notes ="通过用户审核接口" )
    @PutMapping("/activateAccount")
    @Repeat(lockTime = 600)
    public ApiResponse<Void> activateAccount(@RequestBody ActivateAccountRequest request){
        tSysUserService.ActivateAccount(request);
        return ApiResponse.ok();
    }

    @PutMapping("/rejectAccount")
    @ApiOperation(value = "拒绝用户审核",notes = "拒绝用户审核接口")
    public ApiResponse<Void> rejectAccount(@RequestBody RejectAccountRequest request){
        tSysUserService.rejectAccount(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "列表查询用户企业认证信息",notes = "列表查询用户企业认证信息接口")
    @PostMapping("/listUserEnterpriseCertification")
    public ApiResponse<IPage<UserEnterpriseCertificationDTO>> listUserEnterpriseCertification(@RequestBody PageQueryUserEnterpriseCertificationRequest request){
        return ApiResponse.ok(userEnterpriseCertificationService.listUserEnterpriseCertification(request));
    }

    @ApiOperation(value = "审核用户企业认证信息",notes = "审核用户企业认证信息接口")
    @PostMapping("/applyUserEnterpriseCertification")
    public ApiResponse<Void> applyUserEnterpriseCertification(@RequestBody applyUserEnterpriseCertificationRequest request){
        userEnterpriseCertificationService.apply(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "修改用户登录限制配置",notes = "修改用户登录限制配置")
    @PostMapping("/modifyUserLoginConfiguration")
    public ApiResponse<Void> modifyUserLoginConfiguration(@Valid  @RequestBody modifyUserLoginConfigurationRequest request){
        userLoginConfigurationService.modifyUserLoginConfiguration(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "查看用户登录限制配置",notes = "查看用户登录限制配置")
    @PostMapping("/getUserLoginConfiguration")
    public ApiResponse<UserLoginConfigurationDTO> getUserLoginConfiguration(){
        return ApiResponse.ok(userLoginConfigurationService.getUserLoginConfiguration());
    }

}