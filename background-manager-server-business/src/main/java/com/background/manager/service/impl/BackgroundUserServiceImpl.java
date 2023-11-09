package com.background.manager.service.impl;

import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.config.BCryptPasswordEncoderUtil;
import com.background.manager.constants.UserStatusConstant;
import com.background.manager.convert.BackgroundRoleConvertor;
import com.background.manager.convert.BackgroundUserConvertor;
import com.background.manager.convert.BackgroundUserInfoConvertor;
import com.background.manager.dto.request.user.*;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.BackgroundRole;
import com.background.manager.model.BackgroundUser;
import com.background.manager.model.BackgroundUserInfo;
import com.background.manager.model.BackgroundUserRole;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundUserMapper;
import com.background.manager.service.*;
import com.background.manager.util.IpUtils;
import com.background.manager.util.LoginHelper;
import com.background.manager.util.Md5Util;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
@Slf4j
public class BackgroundUserServiceImpl extends ServiceImpl<BackgroundUserMapper, BackgroundUser> implements BackgroundUserService {

    @Resource
    private BackgroundUserConvertor backgroundUserConvertor;
    @Resource
    private BackgroundUserInfoService backgroundUserInfoService;
    @Resource
    private BackgroundUserRoleService backgroundUserRoleService;
    @Resource
    private BackgroundRoleService backgroundRoleService;
    @Resource
    private BackgroundRoleConvertor backgroundRoleConvertor;
    @Resource
    private BackgroundLoginLogService backgroundLoginLogService;
    @Resource
    private BackgroundUserInfoConvertor backgroundUserInfoConvertor;
    @Autowired
    private BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;

    /**
     * 重置账号登录失败次数
     */
    private Consumer<BackgroundUser> resetLoginFailNum = backgroundUser -> {
        backgroundUser.setLoginFailNum(0);
        this.updateById(backgroundUser);
    };

    @Override
    @Transactional
    public String register(BackgroundUserRegisterRequest request) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getLoginId, request.getLoginId()));
        if (ObjectUtil.isNotNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_EXIT_ERROR);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.PASSWORD_INCONSISTENT_ERROR);
        }
        backgroundUser = new BackgroundUser();
        backgroundUser.setLoginId(request.getLoginId());
        backgroundUser.setPassword(Md5Util.MD5Encode(request.getPassword()));
        backgroundUser.setStatus(UserStatusConstant.NORMAL_STATUS);
        backgroundUser.setCreator(request.getLoginId());
        backgroundUser.setCreatorIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        backgroundUser.setModifier(request.getLoginId());
        backgroundUser.setModifierIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        this.save(backgroundUser);
        //更新BackgroundInfo用户信息表
        BackgroundUserInfo backgroundUserInfo = new BackgroundUserInfo();
        backgroundUserInfo.setUserId(backgroundUser.getId());
        backgroundUserInfo.setStatus(UserStatusConstant.NORMAL_STATUS);
        backgroundUserInfo.setCreator(request.getLoginId());
        backgroundUserInfo.setCreatorIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        backgroundUserInfo.setModifier(request.getLoginId());
        backgroundUserInfo.setModifierIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        backgroundUserInfoService.save(backgroundUserInfo);
        return backgroundUser.getLoginId();
    }

    @Override
    public LoginUserInfoDTO login(BackgroundUserLoginRequest request, HttpServletRequest httpServletRequest) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getLoginId, request.getLoginId()));
        if (ObjectUtil.isNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        //校验账号是否封禁
        if (StpUtil.isDisable(request.getLoginId())) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ACCOUNT_DISABLE);
        }
        if (backgroundUser.getPassword().equals(Md5Util.MD5Encode(request.getPassword()))) {
            if (UserStatusConstant.NOT_REMEMBER_ME.equals(request.getRememberMe())) {
                //重置账户登录失败次数
                resetLoginFailNum.accept(backgroundUser);
                LoginHelper.login(backgroundUser);
            }
            //"记住我"模式
            if (UserStatusConstant.REMEMBER_ME.equals(request.getRememberMe())) {
                LoginHelper.AutomaticLogin(backgroundUser);
            }
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            LoginUserInfoDTO userInfoByLoginId = backgroundUserInfoService.findUserInfoByLoginId(request.getLoginId());
            userInfoByLoginId.setAdministratorFlag(backgroundUser.getAdministratorFlag());
            userInfoByLoginId.setTokenInfo(tokenInfo);
            return userInfoByLoginId;
        } else {
            backgroundUser.setLoginFailNum(backgroundUser.getLoginFailNum() + 1);
            this.updateById(backgroundUser);
            if (UserStatusConstant.LOGIN_FAIL_NUM.equals(backgroundUser.getLoginFailNum().intValue())) {
                StpUtil.disable(request.getLoginId(), UserStatusConstant.DISABLE_TIME);
                resetLoginFailNum.accept(backgroundUser);
            }
            throw new BadRequestException(BackgroundManagementResultCodeEnum.PASSWORD_INCORRECT_ERROR);
        }
    }

    @Override
    public void logout() {
        StpUtil.logout(StpUtil.getLoginId());
    }

    @Override
    @Transactional
    public Boolean freeze(Long id) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, id));
        if (ObjectUtil.isNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.update(new LambdaUpdateWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, id)
                .set(BackgroundUser::getStatus, UserStatusConstant.FREEZE_STATUS));
        //修改用户信息UserInfo的状态
        backgroundUserInfoService.freeze(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean unFreeze(Long id) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, id));
        if (ObjectUtil.isNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.update(new LambdaUpdateWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, id)
                .set(BackgroundUser::getStatus, UserStatusConstant.NORMAL_STATUS));
        backgroundUserInfoService.unFreeze(id);
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, id));
        if (ObjectUtil.isNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.removeById(id);
        backgroundUserInfoService.delete(id);
        return true;
    }

    @Override
    public List<RoleDTO> getRoles(Long id) {
        List<Long> roleIds = backgroundUserRoleService.getRoleIds(id);
        if (CollectionUtils.isNotEmpty(roleIds)) {
            return backgroundRoleService.getRoleByRoleIds(roleIds);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean modifyPassword(ModifyUserPasswordRequest request) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        if (!backgroundUser.getPassword().equals(Md5Util.MD5Encode(request.getOldPassword()))) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.PASSWORD_INCORRECT_ERROR);
        }
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.PASSWORD_INCONSISTENT_ERROR);
        }
        return this.update(new LambdaUpdateWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, request.getId())
                .set(BackgroundUser::getPassword, Md5Util.MD5Encode(request.getNewPassword())));
    }

    @Override
    public BackgroundUser getUser() {
        String loginId = StpUtil.getLoginIdAsString();
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getLoginId, loginId));
        if (ObjectUtil.isNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return backgroundUser;
    }

    @Override
    public BackgroundUserInfo getUserInfo() {
        if (ObjectUtil.isNull(StpUtil.getLoginIdDefaultNull())) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.NOT_LOGIN_ERROR);
        }
        BackgroundUser backgroundUser = this.getUser();
        BackgroundUserInfo backgroundUserInfo = backgroundUserInfoService.getOne(new LambdaQueryWrapper<BackgroundUserInfo>()
                .eq(BackgroundUserInfo::getUserId, backgroundUser.getId()));
        if (ObjectUtil.isNull(backgroundUserInfo)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return backgroundUserInfo;
    }

    @Override
    @Transactional
    public void assignRole(Long userId, List<Long> roleIds) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, userId));
        if (ObjectUtil.isNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        if (UserStatusConstant.FREEZE_STATUS.equals(backgroundUser.getStatus())) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.USER_FREEZE_ERROR);
        }
        /*校验角色*/
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                BackgroundRole role = backgroundRoleService.getRoleByRoleId(roleId);
                if (ObjectUtil.isNull(role)) {
                    throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NOT_EXIT_ERROR);
                }
                if (UserStatusConstant.FREEZE_ROLE_STATUS.equals(role.getStatus())) {
                    throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_FREEZE_ERROR);
                }
            }
            //清除用户之前的角色
            backgroundUserRoleService.clean(userId);
            //设置新角色
            for (Long roleId : roleIds) {
                BackgroundUserRole backgroundUserRole = new BackgroundUserRole();
                backgroundUserRole.setUserId(userId);
                backgroundUserRole.setRoleId(roleId);
                backgroundUserRoleService.save(backgroundUserRole);
            }
        }
    }

    @Override
    @Transactional
    public String add(AddUserRequest request) {
        //校验登录账号是否重复
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getLoginId, request.getLoginId()));
        if (ObjectUtil.isNotNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.USER_LOGIN_ID_REPEAT_ERROR);
        }
        //校验添加的角色是否都存在
        if (CollectionUtils.isNotEmpty(request.getRoleId())) {
            if (!this.vaildRole(request.getRoleId())) {
                throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NOT_EXIT_ERROR);
            }
        }
        //添加用户
        BackgroundUser addBackgroundUser = new BackgroundUser();
        addBackgroundUser.setLoginId(request.getLoginId());
        addBackgroundUser.setStatus(UserStatusConstant.NORMAL_STATUS);
        addBackgroundUser.setPassword(Md5Util.MD5Encode(request.getPassword()));
        if (StpUtil.isLogin()) {
            addBackgroundUser.setCreator(StpUtil.getLoginIdAsString());
            addBackgroundUser.setModifier(StpUtil.getLoginIdAsString());
        }
        addBackgroundUser.setCreatorIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        addBackgroundUser.setModifierIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        this.save(addBackgroundUser);
        //完善用户个人信息
        BackgroundUserInfo backgroundUserInfo = backgroundUserInfoConvertor.toBackgroundUserInfo(request);
        backgroundUserInfo.setUserId(addBackgroundUser.getId());
        backgroundUserInfo.setStatus(UserStatusConstant.NORMAL_STATUS);
        if (StpUtil.isLogin()) {
            backgroundUserInfo.setCreator(StpUtil.getLoginIdAsString());
            backgroundUserInfo.setModifier(StpUtil.getLoginIdAsString());
        }
        backgroundUserInfo.setCreatorIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        backgroundUserInfo.setModifierIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        backgroundUserInfoService.save(backgroundUserInfo);
        //添加角色
        if (CollectionUtils.isNotEmpty(request.getRoleId())) {
            this.assignRole(addBackgroundUser.getId(), request.getRoleId());
        }
        return addBackgroundUser.getLoginId();
    }

    @Override
    @Transactional
    public void edit(ModifyUserRequest request) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundUser)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        //校验角色是否都存在
        if (CollectionUtils.isNotEmpty(request.getRoleId())) {
            if (!this.vaildRole(request.getRoleId())) {
                throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NOT_EXIT_ERROR);
            }
        }
        //更新用户对象
        backgroundUser.setLoginId(request.getLoginId());
        if (StpUtil.isLogin()) {
            backgroundUser.setModifier(StpUtil.getLoginIdAsString());
        }
        backgroundUser.setModifierIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        //更新用户个人信息
        BackgroundUserInfo backgroundUserInfo = backgroundUserInfoConvertor.toBackgroundUserInfo(request);
        if (StpUtil.isLogin()) {
            backgroundUserInfo.setModifier(StpUtil.getLoginIdAsString());
        }
        backgroundUserInfo.setModifierIp(IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        //更新角色
        this.assignRole(request.getId(), request.getRoleId());
        this.updateById(backgroundUser);
        backgroundUserInfoService.updateById(backgroundUserInfo);
    }

    @Override
    public BackgroundUser getUserByUserId(Long userId) {
        BackgroundUser backgroundUser = this.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getId, userId));
        return backgroundUser;
    }

    @Override
    public boolean checkLogin() {
        return StpUtil.isLogin();
    }

    /**
     * Description: 校验角色是否都存在
     *
     * @param roleId 角色id
     * @return boolean
     * @author 杜黎明
     * @date 2023/01/13 17:26:53
     */
    private boolean vaildRole(List<Long> roleId) {
        return backgroundRoleService.vaildRole(roleId);
    }

    public static void main(String[] args) {
        String password = "E9]K1417";
        System.out.println(password);
        byte[] passwordb = password.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(passwordb));
        try {
            BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil1 = new BCryptPasswordEncoderUtil();
            String encode = bCryptPasswordEncoderUtil1.encode(password);
            System.out.println(encode);
            System.out.println(bCryptPasswordEncoderUtil1.matches(password, encode));
            String BCrypt="$2a$10$56UHqBfpba6IZJpuxUTA..ZN3AoucY/PR4IEL8jPgZTmA48HKU2mu";
            System.out.println(bCryptPasswordEncoderUtil1.matches(password,BCrypt));
            String usernameP="Nbicc@123";
            String bcryptPassword="$2a$10$OXERsrgblW9Qs.2WvOiPUebfnLLxLMECpFwzDuxxo//.MdUrnPxEO";
            System.out.println(bCryptPasswordEncoderUtil1.matches(usernameP,bcryptPassword));
            System.out.println(bCryptPasswordEncoderUtil1);

        } catch (Exception e) {
            throw new RuntimeException(e);
//        }
        }

    }
}



