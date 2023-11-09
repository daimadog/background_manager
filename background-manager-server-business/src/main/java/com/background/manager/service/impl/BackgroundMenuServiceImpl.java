package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.constants.UserStatusConstant;
import com.background.manager.convert.BackgroundMenuConvertor;
import com.background.manager.dto.request.permission.AddPermsRequest;
import com.background.manager.dto.request.permission.ModifyPermsRequest;
import com.background.manager.dto.request.permission.QueryPermsRequest;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.dto.response.permission.PermissionDigestDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.BackgroundMenu;
import com.background.manager.model.BackgroundUser;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundMenuMapper;
import com.background.manager.service.BackgroundMenuService;
import com.background.manager.service.BackgroundRoleMenuService;
import com.background.manager.service.BackgroundUserRoleService;
import com.background.manager.service.BackgroundUserService;
import com.background.manager.util.LoginHelper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackgroundMenuServiceImpl extends ServiceImpl<BackgroundMenuMapper, BackgroundMenu> implements BackgroundMenuService {
    @Resource
    private BackgroundMenuConvertor backgroundMenuConvertor;
    @Resource
    private BackgroundUserService backgroundUserService;
    @Resource
    private BackgroundUserRoleService backgroundUserRoleService;
    @Resource
    private BackgroundRoleMenuService backgroundRoleMenuService;


    @Override
    public List<PermissionDTO> getPermissionList() {
        List<BackgroundMenu> backgroundMenuList = this.list(new LambdaQueryWrapper<BackgroundMenu>());
        return backgroundMenuConvertor.toPermissionDTOList(backgroundMenuList);
    }

    @Override
    public List<PermissionDTO> getPermissionListById(String loginId) {
        List<PermissionDTO> permissionDTOList = new ArrayList<>();
        BackgroundUser backgroundUser=LoginHelper.getLoginUser();
        if (ObjectUtil.isNull(backgroundUser)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
            List<Long> roleIdList = backgroundUserRoleService.getRoleIds(backgroundUser.getId());
            if (CollectionUtil.isNotEmpty(roleIdList)) {
                List<Long> permissionIdList = backgroundRoleMenuService.getPermissionIdListByRoleIdList(roleIdList);
                List<BackgroundMenu> backgroundMenus = this.list(
                        new LambdaQueryWrapper<BackgroundMenu>()
                        .in(BackgroundMenu::getId, permissionIdList));
                permissionDTOList=backgroundMenuConvertor.toPermissionDTOList(backgroundMenus);
            }
            return permissionDTOList;
    }

    @Override
    public List<PermissionDigestDTO> listQuery(QueryPermsRequest request) {
        List<BackgroundMenu> backgroundMenuList = this.list(new LambdaQueryWrapper<BackgroundMenu>()
                .like(StringUtils.isNotBlank(request.getMenuName()), BackgroundMenu::getMenuName, request.getMenuName())
                .eq(ObjectUtil.isNotNull(request.getStatus()), BackgroundMenu::getStatus, request.getStatus())
                .orderByAsc(BackgroundMenu::getCreateTime));
        if (CollectionUtil.isEmpty(backgroundMenuList)){
            return new ArrayList<>();
        }
        //封装权限树形结构
        return this.treePermsList(backgroundMenuList);
    }

    @Override
    public String add(AddPermsRequest request) {
        //校验权限名称是否重复
        BackgroundMenu repeatBackgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                .eq(BackgroundMenu::getMenuName, request.getMenuName()));
        if (ObjectUtil.isNotNull(repeatBackgroundMenu)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.MENU_NAME_REPEAT_ERROR);
        }
        // todo 校验路由地址是否符合规范
        //校验路由地址是否存在
        if (StringUtils.isNotBlank(request.getPath())){
            BackgroundMenu pathBackgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                    .eq(BackgroundMenu::getPath, request.getPath()));
            if (ObjectUtil.isNotNull(pathBackgroundMenu)){
                throw new BadRequestException(BackgroundManagementResultCodeEnum.PATH_EXIT_ERROR);
            }
        }
        BackgroundMenu backgroundMenu=backgroundMenuConvertor.toBackgroundMenu(request);
        if (ObjectUtils.isNotEmpty(StpUtil.getLoginIdDefaultNull())) {
            String creator = StpUtil.getLoginIdAsString();
            backgroundMenu.setCreator(creator);
            backgroundMenu.setModifier(creator);
        }
        this.save(backgroundMenu);
        return backgroundMenu.getMenuName();
    }

    @Override
    public boolean modify(ModifyPermsRequest request) {
        BackgroundMenu repeatBackgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                .eq(BackgroundMenu::getMenuName, request.getMenuName())
                .ne(BackgroundMenu::getId,request.getId()));
        if (ObjectUtil.isNotNull(repeatBackgroundMenu)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.MENU_NAME_REPEAT_ERROR);
        }
        // todo 校验路由地址是否符合规范
        //校验路由地址是否存在
        if (StringUtils.isNotBlank(request.getPath())){
            BackgroundMenu pathBackgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                    .eq(BackgroundMenu::getPath, request.getPath())
                    .ne(BackgroundMenu::getId,request.getId()));
            if (ObjectUtil.isNotNull(pathBackgroundMenu)){
                throw new BadRequestException(BackgroundManagementResultCodeEnum.PATH_EXIT_ERROR);
            }
        }
        BackgroundMenu backgroundMenu=backgroundMenuConvertor.toBackgroundMenu(request);
        backgroundMenu.setModifier(StpUtil.getLoginIdAsString());
        backgroundMenu.setModifyTime(LocalDateTime.now());
        return this.updateById(backgroundMenu);
    }

    @Override
    public boolean delete(Long id) {
        BackgroundMenu backgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                .eq(BackgroundMenu::getId, id));
        if (ObjectUtil.isNull(backgroundMenu)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        if (UserStatusConstant.FIRST_MENU_ID.equals(backgroundMenu.getParentId().intValue())){
            /**如果是父节点，要删除所属的子节点*/
            List<BackgroundMenu> childBackgroundMenus = this.list(new LambdaQueryWrapper<BackgroundMenu>()
                    .eq(BackgroundMenu::getParentId, backgroundMenu.getId()));
            if (CollectionUtil.isNotEmpty(childBackgroundMenus)){
                for (BackgroundMenu child:childBackgroundMenus){
                    child.setIsDeleted(UserStatusConstant.DELETE_STATUS);
                }
            }
        }
        return this.removeById(id);
        //todo 是否需要删除相应的角色-权限引用？
    }

    @Override
    public BackgroundMenu getPermissionByPermId(Long premId) {
        BackgroundMenu backgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                .eq(BackgroundMenu::getId, premId));
        if (ObjectUtil.isNull(backgroundMenu)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.PERMISSION_NOT_EXIT_ERROR);
        }
        return backgroundMenu;
    }

    @Override
    public List<PermissionDTO> getPermissionByPermIdList(List<Long> permissionIdList) {
        List<BackgroundMenu> backgroundMenuList = this.list(new LambdaQueryWrapper<BackgroundMenu>()
                .in(BackgroundMenu::getId, permissionIdList));
        return backgroundMenuConvertor.toPermissionDTOList(backgroundMenuList);
    }

    @Override
    public List<PermissionDTO> getAllPermissionList() {
        List<BackgroundMenu> backgroundMenuList = this.list(new LambdaQueryWrapper<BackgroundMenu>());
        return backgroundMenuConvertor.toPermissionDTOList(backgroundMenuList);
    }

    @Override
    public Boolean getNoParentPermissionDTO(Long id, List<Long> permissionIdList) {
        BackgroundMenu backgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                .eq(BackgroundMenu::getId, id)
                .notIn(BackgroundMenu::getParentId, permissionIdList));
        return ObjectUtil.isNotNull(backgroundMenu)? true:false;
    }

    @Override
    public boolean existChildren(Long id) {
        List<BackgroundMenu> backgroundMenuList = this.list(new LambdaQueryWrapper<BackgroundMenu>()
                .eq(BackgroundMenu::getParentId, id));
        return CollectionUtil.isNotEmpty(backgroundMenuList)?true:false;
    }

    @Override
    public boolean existMenu(Long id) {
        BackgroundMenu backgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                .eq(BackgroundMenu::getId, id)
        );
        if (ObjectUtil.isNotNull(backgroundMenu)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Description: 树形结构展示权限菜单
     * @param records
     * @return {@link List }<{@link PermissionDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/12 15:21:26
     */
    private List<PermissionDigestDTO> treePermsList(List<BackgroundMenu> records) {
        List<PermissionDigestDTO> permsDigestTreeList=new ArrayList<>();
        if (CollectionUtil.isEmpty(records)){
            return new ArrayList<>();
        }
        /**添加父节点**/
//        List<BackgroundMenu> parentMenuList=new ArrayList<>();
//        if (CollUtil.isNotEmpty(records)){
//            records.forEach(record->{
//                if (UserStatusConstant.FIRST_MENU_ID.equals(record.getParentId().intValue())){
//                    parentMenuList.add(record);
//                }
//            });
//        }
        List<PermissionDigestDTO> parentList=new ArrayList<>();
        List<PermissionDigestDTO> permsDigestList = backgroundMenuConvertor.toPermissionDigestDTOS(records);
        if (CollectionUtil.isNotEmpty(permsDigestList)){
            permsDigestList.forEach(permissionDigestDTO ->{
            if (UserStatusConstant.FIRST_MENU_ID.equals(permissionDigestDTO.getParentId().intValue())){
                parentList.add(permissionDigestDTO);
            }
        });
        permsDigestList.forEach(permissionDigestDTO ->{
            if (existChildren(permissionDigestDTO.getId())&&NotParent(permissionDigestDTO,parentList)
                    &&!UserStatusConstant.FIRST_MENU_ID.equals(permissionDigestDTO.getParentId().intValue())){
                parentList.add(permissionDigestDTO);
            }
        });
        }
        List<PermissionDigestDTO> allPermissionDigestDTO=this.getAllPermissionDigestDTO();
        /**采用递归算法，为一级节点设置子节点*/
        if (CollectionUtil.isNotEmpty(parentList)){
            for (PermissionDigestDTO amp:parentList){
                PermissionDigestDTO permissionDigestDTO=new PermissionDigestDTO();
                permissionDigestDTO=amp;
                permissionDigestDTO.setChildren(getChildrenPerms(amp.getId(),allPermissionDigestDTO));
                permsDigestTreeList.add(amp);
            }
        }
        if (CollectionUtil.isEmpty(permsDigestTreeList)){
            return permsDigestList;
        }
        return permsDigestTreeList;
    }

    /**
     * Description: 存在子节点并且父节点不在parentList中
     * @param parentList 父列表
     * @return boolean
     * @author 杜黎明
     * @date 2023/01/17 15:19:50
     */
    private boolean NotParent(PermissionDigestDTO permissionDigestDTO, List<PermissionDigestDTO> parentList) {
        BackgroundMenu backgroundMenu = this.getOne(new LambdaQueryWrapper<BackgroundMenu>()
                .eq(BackgroundMenu::getId, permissionDigestDTO.getParentId()));
        if (ObjectUtil.isNotNull(backgroundMenu)){
            if (parentList.contains(backgroundMenuConvertor.toPermissionDigestDTO(backgroundMenu))){
                return false;
            }
        }else {
            return true;
        }
        NotParent(backgroundMenuConvertor.toPermissionDigestDTO(backgroundMenu),parentList);
        return true;
    }

    /**
     * Description: 查询所有的权限列表
     * @return {@link List }<{@link PermissionDigestDTO }>
     * @author 杜黎明
     * @date 2022/11/07 16:26:33
     */
    private List<PermissionDigestDTO> getAllPermissionDigestDTO() {
        List<BackgroundMenu> backgroundMenuList = this.list(new LambdaQueryWrapper<BackgroundMenu>());
        return backgroundMenuConvertor.toPermissionDigestDTOS(backgroundMenuList);
    }

    /**
     * Description: 获取子权限集合
     * @param id
     * @param permissionDigestDTOS
     * @return {@link List }<{@link PermissionDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/12 15:42:03
     */
    private List<PermissionDigestDTO> getChildrenPerms(Long id, List<PermissionDigestDTO> permissionDigestDTOS) {
        List<PermissionDigestDTO> childrenList = new ArrayList<>();
        for (PermissionDigestDTO children:permissionDigestDTOS){
            if (id.equals(children.getParentId())){
                childrenList.add(children);
            }
        }
        //循环子节点
        for (PermissionDigestDTO childrenPermissionDigestDTO:childrenList){
            childrenPermissionDigestDTO.setChildren(getChildrenPerms(childrenPermissionDigestDTO.getId(),permissionDigestDTOS));
        }
        return childrenList;
    }


}
