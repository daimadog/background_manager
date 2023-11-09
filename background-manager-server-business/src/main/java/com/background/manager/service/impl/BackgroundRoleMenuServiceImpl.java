package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.constants.UserStatusConstant;
import com.background.manager.convert.BackgroundMenuConvertor;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.BackgroundMenu;
import com.background.manager.model.BackgroundRoleMenu;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundRoleMenuMapper;
import com.background.manager.service.BackgroundMenuService;
import com.background.manager.service.BackgroundRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BackgroundRoleMenuServiceImpl extends ServiceImpl<BackgroundRoleMenuMapper, BackgroundRoleMenu> implements BackgroundRoleMenuService {

    @Resource
    private BackgroundMenuService backgroundMenuService;
    @Resource
    private BackgroundMenuConvertor backgroundMenuConvertor;

    @Override
    public List<Long> getPermissionIdList(Long roleId) {
        List<Long> permissionIdList=new ArrayList<>();
        List<BackgroundRoleMenu> backgroundRoleMenus = this.list(new LambdaQueryWrapper<BackgroundRoleMenu>()
                .eq(BackgroundRoleMenu::getRoleId, roleId));
        if (CollectionUtil.isNotEmpty(backgroundRoleMenus)){
            for (BackgroundRoleMenu backgroundRoleMenu:backgroundRoleMenus){
                permissionIdList.add(backgroundRoleMenu.getPermissionId());
            }
        }
        return permissionIdList;
    }

    @Override
    public BackgroundRoleMenu getRoleMenuById(Long id) {
        return this.getOne(lambdaQuery().eq(BackgroundRoleMenu::getRoleId,id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPerms(Long roleId, List<Long> permissionIds) {
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            /**校验权限菜单**/
            for (Long premId : permissionIds) {
                BackgroundMenu permission = backgroundMenuService.getPermissionByPermId(premId);
                if (UserStatusConstant.FREEZE_STATUS.equals(permission.getStatus())) {
                    throw new BadRequestException(BackgroundManagementResultCodeEnum.PERMISSION_FREEZE_ERROR);
                }
            }
            /**清除之前的角色-权限关联体**/
            this.remove(new LambdaQueryWrapper<BackgroundRoleMenu>()
                    .eq(BackgroundRoleMenu::getRoleId, roleId));

            /**新增角色-权限关联体**/
            List<BackgroundRoleMenu> backgroundRoleMenus=new ArrayList<>();
            for (Long permissionId : permissionIds) {
                BackgroundRoleMenu backgroundRoleMenu = new BackgroundRoleMenu();
                backgroundRoleMenu.setRoleId(roleId);
                backgroundRoleMenu.setPermissionId(permissionId);
                backgroundRoleMenus.add(backgroundRoleMenu);
            }
                this.saveBatch(backgroundRoleMenus);
        }
    }

    @Override
    public void delete(Long id) {
        this.remove(new LambdaQueryWrapper<BackgroundRoleMenu>()
                .eq(BackgroundRoleMenu::getRoleId, id));
    }

    @Override
    public List<PermissionDTO> getPerms(Long id) {
        List<PermissionDTO> permissionTreeList = new ArrayList<>();
        List<BackgroundRoleMenu> backgroundRoleMenus = this.list(new LambdaQueryWrapper<BackgroundRoleMenu>()
                .eq(BackgroundRoleMenu::getRoleId, id));
        if (CollectionUtil.isNotEmpty(backgroundRoleMenus)) {
            //获取当前角色下的所有权限集合List<PermissionDTO>
            List<Long> permissionIdList = new ArrayList<>();
            for (BackgroundRoleMenu backgroundRoleMenu : backgroundRoleMenus) {
                permissionIdList.add(backgroundRoleMenu.getPermissionId());
            }
            List<PermissionDTO> permissionDTOList = backgroundMenuService.getPermissionByPermIdList(permissionIdList);
            //封装权限树形集合
            /**封装一级节点*/
            if (CollectionUtil.isNotEmpty(permissionDTOList)) {
                List<PermissionDTO> parentList = new ArrayList<>();
                for (PermissionDTO permissionDTO : permissionDTOList) {
                    if(UserStatusConstant.FIRST_MENU_ID.equals(permissionDTO.getParentId().intValue())){
                        parentList.add(permissionDTO);
                    }else if (backgroundMenuService.existMenu(permissionDTO.getParentId())) {
                        BackgroundMenu parent = backgroundMenuService.getPermissionByPermId(permissionDTO.getParentId());
                        parentList.add(backgroundMenuConvertor.toPermissionDTO(parent));
                    }
                }
                /**采用递归算法，为一级节点设置子节点*/
                List<PermissionDTO> distinctParents = parentList.stream().distinct().collect(Collectors.toList());
                if (CollectionUtil.isNotEmpty(distinctParents)) {
                    for (PermissionDTO amp : distinctParents) {
                        PermissionDTO permissionDTO = new PermissionDTO();
                        permissionDTO = amp;
                        permissionDTO.setChildren(getChildrenPerms(amp.getId(), permissionDTOList));
                        permissionTreeList.add(amp);
                    }
                }
            }
            if (CollectionUtil.isEmpty(permissionTreeList)){
                return permissionDTOList;
            }
        }
            return permissionTreeList;
    }

    private List<PermissionDTO> getChildrenPerms(Long id, List<PermissionDTO> permissionDTOList) {
        List<PermissionDTO> childrenList = new ArrayList<>();
        for (PermissionDTO children:permissionDTOList){
            if (id.equals(children.getParentId())){
                childrenList.add(children);
            }
        }
        //循环子节点
        for (PermissionDTO childrenPermissionDigestDTO:childrenList){
            childrenPermissionDigestDTO.setChildren(getChildrenPerms(childrenPermissionDigestDTO.getId(),permissionDTOList));
        }
        return childrenList;
    }

    /**
     * Description: 是否含子节点children
     * @param permissionDTO 许可dto
     * @return boolean
     * @author 杜黎明
     * @date 2022/11/07 14:51:30
     */
    private boolean existChildren(PermissionDTO permissionDTO) {
        return backgroundMenuService.existChildren(permissionDTO.getId());
    }

    /**
     * Description:获取当前权限的子节点权限
     * @param id 权限id
     * @param permissionDTOList
     * @return {@link List }<{@link PermissionDTO }>
     * @author 杜黎明
     * @date 2022/10/19 17:28:17
     */
    private List<PermissionDTO> getChildren(Long id, List<PermissionDTO> permissionDTOList) {
        List<PermissionDTO> childrenList=new ArrayList<>();
        for (PermissionDTO permissionDTO:permissionDTOList){
            if (ObjectUtil.equal(id,permissionDTO.getParentId())){
                childrenList.add(permissionDTO);
            }
        }
            for (PermissionDTO children:childrenList){
                children.setChildren(getChildren(children.getId(),permissionDTOList));
            }
       return childrenList;
    }

    @Override
    public void deleteByPermId(Long menuId) {
        List<BackgroundRoleMenu> backgroundRoleMenus = this.list(new LambdaQueryWrapper<BackgroundRoleMenu>()
                .eq(BackgroundRoleMenu::getPermissionId, menuId));
        if (CollectionUtil.isNotEmpty(backgroundRoleMenus)){
            for (BackgroundRoleMenu backgroundRoleMenu:backgroundRoleMenus){
                 this.remove(new LambdaQueryWrapper<BackgroundRoleMenu>()
                        .eq(BackgroundRoleMenu::getPermissionId, backgroundRoleMenu.getPermissionId()));
            }
        }
    }

    @Override
    public List<Long> getPermissionIdListByRoleIdList(List<Long> roleIdList) {
        if (CollectionUtil.isNotEmpty(roleIdList)){
            List<BackgroundRoleMenu> backgroundRoleMenus = this.list(new LambdaQueryWrapper<BackgroundRoleMenu>()
                    .in(BackgroundRoleMenu::getRoleId, roleIdList));
            List<Long> permissionIdList = backgroundRoleMenus.stream().map(BackgroundRoleMenu::getPermissionId)
                                .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(permissionIdList)){
                return permissionIdList;
            }
        }
        return new ArrayList<>();
    }

}
