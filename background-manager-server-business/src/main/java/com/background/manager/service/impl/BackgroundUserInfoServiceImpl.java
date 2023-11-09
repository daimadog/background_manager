package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.constants.UserStatusConstant;
import com.background.manager.convert.BackgroundUserInfoConvertor;
import com.background.manager.dto.request.user.ModifyUserInfoRequest;
import com.background.manager.dto.request.user.PageQueryUserInfoRequest;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.dto.response.user.UserInfoDTO;
import com.background.manager.dto.response.user.UserInfoDigestDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.BackgroundUser;
import com.background.manager.model.BackgroundUserInfo;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundUserInfoMapper;
import com.background.manager.service.BackgroundRoleService;
import com.background.manager.service.BackgroundUserInfoService;
import com.background.manager.service.BackgroundUserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.background.manager.constants.UserStatusConstant.ADMINISTRATOR_USER;

@Service
public class BackgroundUserInfoServiceImpl extends ServiceImpl<BackgroundUserInfoMapper, BackgroundUserInfo> implements BackgroundUserInfoService {

    @Resource
    private BackgroundUserInfoConvertor backgroundUserInfoConvertor;
    @Resource
    private BackgroundUserService backgroundUserService;
    @Resource
    private BackgroundRoleService backgroundRoleService;

    @Override
    public IPage<UserInfoDigestDTO> pageQuery(PageQueryUserInfoRequest request) {
        Page<BackgroundUserInfo> page=this.lambdaQuery()
                .eq(ObjectUtil.isNotNull(request.getUserId()),BackgroundUserInfo::getUserId,request.getUserId())
                .like(StringUtils.isNotBlank(request.getUsername()),BackgroundUserInfo::getUsername,request.getUsername())
                .orderByDesc(BackgroundUserInfo::getCreateTime)
                .page(new Page<>(request.getPage(),request.getSize()));
        if (CollectionUtils.isEmpty(page.getRecords())){
            return new Page<>();
        }
        List<UserInfoDigestDTO> userInfoDigestDTOS = new ArrayList<>();
        for (BackgroundUserInfo backgroundUserInfo:page.getRecords()) {
            //添加非管理员用户
            BackgroundUser adminUser = backgroundUserService.getOne(new LambdaQueryWrapper<BackgroundUser>()
                    .eq(BackgroundUser::getId, backgroundUserInfo.getUserId()));
            if (!ADMINISTRATOR_USER.equals(adminUser.getAdministratorFlag())) {
                UserInfoDigestDTO userInfoDigestDTO = backgroundUserInfoConvertor.toUserInfoDigestDTO(backgroundUserInfo);
                //设置登录账号userId
                BackgroundUser backgroundUser = backgroundUserService.getUserByUserId(backgroundUserInfo.getUserId());
                if (ObjectUtil.isNull(backgroundUser)) {
                    throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
                }
                userInfoDigestDTO.setAdministratorFlag(backgroundUser.getAdministratorFlag());
                userInfoDigestDTO.setLoginId(backgroundUser.getLoginId());
                //封装用户角色信息
                List<String> roleName = new ArrayList<>();
                List<RoleDTO> roleList = backgroundRoleService.findRoleByUserId(backgroundUserInfo.getUserId());
                roleList.forEach(role -> roleName.add(role.getRoleName()));
                userInfoDigestDTO.setRoleName(roleName);
                userInfoDigestDTOS.add(userInfoDigestDTO);
            }
        }
        Page<UserInfoDigestDTO> pages=new Page<>();
        pages.setCurrent(page.getCurrent())
                .setPages(page.getPages())
                .setSize(page.getSize())
                .setTotal(userInfoDigestDTOS.size())
                .setRecords(userInfoDigestDTOS);
        return pages;
    }

    @Override
    public void freeze(Long id) {
        BackgroundUserInfo backgroundUserInfo = this.getOne(new LambdaQueryWrapper<BackgroundUserInfo>()
                .eq(BackgroundUserInfo::getId, id));
        if (ObjectUtil.isNull(backgroundUserInfo)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.update(new LambdaUpdateWrapper<BackgroundUserInfo>()
                .eq(BackgroundUserInfo::getId,id)
                .set(BackgroundUserInfo::getStatus, UserStatusConstant.FREEZE_STATUS));
    }

    @Override
    public void unFreeze(Long id) {
        BackgroundUserInfo backgroundUserInfo = this.getOne(new LambdaQueryWrapper<BackgroundUserInfo>()
                .eq(BackgroundUserInfo::getId, id));
        if (ObjectUtil.isNull(backgroundUserInfo)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.update(new LambdaUpdateWrapper<BackgroundUserInfo>()
                .eq(BackgroundUserInfo::getId,id)
                .set(BackgroundUserInfo::getStatus, UserStatusConstant.NORMAL_STATUS));
    }

    @Override
    public void delete(Long id) {
        BackgroundUserInfo backgroundUserInfo = this.getOne(new LambdaQueryWrapper<BackgroundUserInfo>()
                .eq(BackgroundUserInfo::getId, id));
        if (ObjectUtil.isNull(backgroundUserInfo)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.removeById(id);
    }

    @Override
    public UserInfoDTO findUserInfoById(Long id) {
        BackgroundUserInfo backgroundUserInfo = this.getOne(new LambdaQueryWrapper<BackgroundUserInfo>()
                .eq(BackgroundUserInfo::getId, id));
        if (ObjectUtil.isNull(backgroundUserInfo)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return backgroundUserInfoConvertor.toUserInfoDTO(backgroundUserInfo);
    }

    @Override
    public boolean modify(ModifyUserInfoRequest request) {
        BackgroundUserInfo backgroundUserInfo = this.getOne(new LambdaQueryWrapper<BackgroundUserInfo>()
                .eq(BackgroundUserInfo::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundUserInfo)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        BackgroundUserInfo modifyBackgroundUserInfo = backgroundUserInfoConvertor.toUserInfo(request);
        modifyBackgroundUserInfo.setModifier(StpUtil.getLoginIdAsString());
        return this.updateById(modifyBackgroundUserInfo);
    }

    @Override
    @DS("master")
    public LoginUserInfoDTO findUserInfoByLoginId(String loginId) {
        return backgroundUserInfoConvertor.toLoginUserInfoDTO(backgroundUserService.getUserInfo());
    }

}
