package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.constants.UserStatusConstant;
import com.background.manager.convert.BackgroundRoleConvertor;
import com.background.manager.dto.request.role.AddRoleRequest;
import com.background.manager.dto.request.role.AssignPermsRequest;
import com.background.manager.dto.request.role.ModifyRoleRequest;
import com.background.manager.dto.request.role.PageQueryRoleRequest;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.role.RoleDetailDTO;
import com.background.manager.dto.response.role.RoleDigestDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.BackgroundRole;
import com.background.manager.model.BackgroundUser;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundRoleMapper;
import com.background.manager.service.BackgroundRoleMenuService;
import com.background.manager.service.BackgroundRoleService;
import com.background.manager.service.BackgroundUserRoleService;
import com.background.manager.service.BackgroundUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackgroundRoleServiceImpl extends ServiceImpl<BackgroundRoleMapper,BackgroundRole> implements BackgroundRoleService {

    @Resource
    private BackgroundUserService backgroundUserService;
    @Resource
    private BackgroundUserRoleService backgroundUserRoleService;
    @Resource
    private BackgroundRoleConvertor backgroundRoleConvertor;
    @Resource
    private BackgroundRoleMenuService backgroundRoleMenuService;


    @Override
    public List<RoleDTO> getRoleByLoginId(String loginId) {
        BackgroundUser backgroundUser=backgroundUserService.getUser();
        if (ObjectUtil.isNull(backgroundUser)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        List<Long> roleIdList=backgroundUserRoleService.getRoleIds(backgroundUser.getId());
        if (CollectionUtils.isNotEmpty(roleIdList)){
            List<BackgroundRole> backgroundRoleList = this.list(new LambdaQueryWrapper<BackgroundRole>()
                    .in(BackgroundRole::getId, roleIdList));
            List<RoleDTO> roleDTOS = backgroundRoleConvertor.toRoleDTOS(backgroundRoleList);
            return  roleDTOS;
        }
        return new ArrayList<>();
    }

    @Override
    public List<RoleDTO> queryAllRole() {
        List<BackgroundRole> backgroundRoles = this.list(new LambdaQueryWrapper<BackgroundRole>()
                .eq(BackgroundRole::getStatus, UserStatusConstant.NORMAL_ROLE_STATUS));
        return backgroundRoleConvertor.toRoleDTOS(backgroundRoles);
    }

    @Override
    public BackgroundRole getRoleByRoleId(Long roleId) {
        BackgroundRole backgroundRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                .eq(BackgroundRole::getId, roleId));
        if (ObjectUtil.isNull(backgroundRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NOT_EXIT_ERROR);
        }
        return backgroundRole;
    }

    @Override
    public IPage<RoleDigestDTO> pageQuery(PageQueryRoleRequest request) {
        IPage<BackgroundRole> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(request.getRoleName()),BackgroundRole::getRoleName,request.getRoleName())
                .eq(ObjectUtil.isNotNull(request.getStatus()),BackgroundRole::getStatus,request.getStatus())
                .orderByDesc(BackgroundRole::getCreateTime)
                .page(new Page<>(request.getPage(),request.getSize()));
        if (CollectionUtils.isEmpty(page.getRecords())){
            return new Page<>();
        }
        Page<RoleDigestDTO> pages=new Page<>(page.getCurrent(),page.getSize());
        pages.setTotal(page.getTotal());
        pages.setPages(page.getPages());
        pages.setRecords(backgroundRoleConvertor.toRoleDigestDTOS(page.getRecords()));
        return pages;
    }

    @Override
    @Transactional
    public String add(AddRoleRequest request) {
        BackgroundRole repeatNameRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                                .eq(BackgroundRole::getRoleName, request.getRoleName()));
        if (ObjectUtil.isNotNull(repeatNameRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NAME_REPEAT_ERROR);
        }
        BackgroundRole repeatEnNameRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                                .eq(BackgroundRole::getEnName, request.getEnName()));
        if (ObjectUtil.isNotNull(repeatEnNameRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_ENGLISH_NAME_REPEAT_ERROR);
        }
        BackgroundRole backgroundRole = backgroundRoleConvertor.toBackgroundRole(request);
        backgroundRole.setStatus(UserStatusConstant.NORMAL_STATUS);
        backgroundRole.setCreator(StpUtil.getLoginIdAsString());
        backgroundRole.setModifier(StpUtil.getLoginIdAsString());
        this.save(backgroundRole);
        /*添加权限*/
        if (CollectionUtils.isNotEmpty(request.getPermissionIds())){
            backgroundRoleMenuService.addPerms(backgroundRole.getId(),request.getPermissionIds());
        }
        return backgroundRole.getRoleName();
    }

    @Override
    public boolean delete(Long id) {
        BackgroundRole backgroundRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                .eq(BackgroundRole::getId, id));
        if (ObjectUtil.isNull(backgroundRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NOT_EXIT_ERROR);
        }
         this.update(new LambdaUpdateWrapper<BackgroundRole>()
                .eq(BackgroundRole::getId,id)
                .set(BackgroundRole::getIsDeleted,UserStatusConstant.DELETE_STATUS));
        backgroundRoleMenuService.delete(id);
        return true;
    }

    @Override
    @Transactional
    public void modify(ModifyRoleRequest request) {
        //判断角色是否停用
        BackgroundRole backgroundRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                .eq(BackgroundRole::getId, request.getId())
                .eq(BackgroundRole::getStatus, UserStatusConstant.FREEZE_ROLE_STATUS));
        if (ObjectUtil.isNotNull(backgroundRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_FREEZE_ERROR);
        }
        //校验角色名称是否重复
        BackgroundRole repeatBackgroundRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                .eq(BackgroundRole::getRoleName, request.getRoleName())
                .ne(BackgroundRole::getId, request.getId()));
        if (ObjectUtil.isNotNull(repeatBackgroundRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NAME_REPEAT_ERROR);
        }
        //校验角色英文名称是否重复
        BackgroundRole repeatEnNameBackgroundRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                .eq(BackgroundRole::getRoleName, request.getEnName())
                .ne(BackgroundRole::getId, request.getId()));
        if (ObjectUtil.isNotNull(repeatEnNameBackgroundRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NAME_REPEAT_ERROR);
        }
        this.update(new LambdaUpdateWrapper<BackgroundRole>()
                .eq(BackgroundRole::getId,request.getId())
                .set(BackgroundRole::getRoleName,request.getRoleName())
                .set(BackgroundRole::getEnName,request.getEnName()));
        //修改相应的角色权限表
        backgroundRoleMenuService.addPerms(request.getId(),request.getPermissionIds());
    }

    @Override
    public void assignPerms(AssignPermsRequest request) {
        BackgroundRole backgroundRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                .eq(BackgroundRole::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NOT_EXIT_ERROR);
        }
        if (UserStatusConstant.FREEZE_ROLE_STATUS.equals(backgroundRole.getStatus())){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_FREEZE_ERROR);
        }
        backgroundRoleMenuService.addPerms(request.getId(),request.getPermissionIds());
    }

    @Override
    public RoleDetailDTO getRoleDetail(Long id) {
        BackgroundRole backgroundRole = this.getOne(new LambdaQueryWrapper<BackgroundRole>()
                .eq(BackgroundRole::getId, id));
        if (ObjectUtil.isNull(backgroundRole)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ROLE_NOT_EXIT_ERROR);
        }
        RoleDetailDTO roleDetailDTO=new RoleDetailDTO();
        roleDetailDTO.setRoleName(backgroundRole.getRoleName());
        roleDetailDTO.setEnName(backgroundRole.getEnName());
        List<PermissionDTO> permissionDTOS = backgroundRoleMenuService.getPerms(id);
        roleDetailDTO.setPermissionDTOList(permissionDTOS);
        return roleDetailDTO;
    }

    @Override
    public List<RoleDTO> getRoleByRoleIds(List<Long> roleIds) {
        List<BackgroundRole> list = this.list(new LambdaQueryWrapper<BackgroundRole>()
                .in(BackgroundRole::getId, roleIds));
        return backgroundRoleConvertor.toRoleDTOS(list);
    }

    @Override
    public List<RoleDTO> findRoleByUserId(Long userId) {
        List<Long> roleIds = backgroundUserRoleService.getRoleIds(userId);
        if (CollUtil.isNotEmpty(roleIds)){
            return this.getRoleByRoleIds(roleIds);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean vaildRole(List<Long> roleId) {
        List<BackgroundRole> list = this.list(new LambdaQueryWrapper<BackgroundRole>()
                .in(BackgroundRole::getId, roleId));
        return roleId.size()==list.size();
    }
}
