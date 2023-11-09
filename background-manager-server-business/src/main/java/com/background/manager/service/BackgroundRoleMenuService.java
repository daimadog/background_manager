package com.background.manager.service;

import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.model.BackgroundRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 角色-菜单关系实现接口类
 * @Author: 杜黎明
 * @Date: 2022/09/30 17:26:54
 * @Version: 1.0.0
 */
public interface BackgroundRoleMenuService extends IService<BackgroundRoleMenu> {

    /**
     * Description: 获取角色id对应的权限id集合
     * @param roleId 角色id
     * @return {@link List }<{@link Long }>
     * @author 杜黎明
     * @date 2022/10/06 13:48:38
     */
    List<Long> getPermissionIdList(Long roleId);

    /**
     * Description: 根据角色id获取角色-权限关联体对象
     * @param id 角色id
     * @return {@link BackgroundRoleMenu }
     * @author 杜黎明
     * @date 2022/10/11 17:37:55
     */
    BackgroundRoleMenu getRoleMenuById(Long id);

    /**
     * Description: 添加权限
     * @param roleId 角色id
     * @param permissionIds
     * @author 杜黎明
     * @date 2022/10/11 17:48:28
     */
    void addPerms(Long roleId, List<Long> permissionIds);

    /**
     * Description: 删除角色-权限关联体
     * @param id 角色id
     * @author 杜黎明
     * @date 2022/10/12 09:34:17
     */
    void delete(Long id);

    /**
     * Description: 获取当前角色的权限
     * @param id 角色id
     * @return {@link List }<{@link PermissionDTO }>
     * @author 杜黎明
     * @date 2022/10/12 10:20:12
     */
    List<PermissionDTO> getPerms(Long id);

    /**
     * Description: 删除角色-权限关联体
     * @param menuId 权限id
     * @author 杜黎明
     * @date 2022/10/13 16:31:31
     */
    void deleteByPermId(Long menuId);

    /**
     * Description: 根据角色id集合获取对应的所有权限id
     * @param roleIdList
     * @return {@link List }<{@link Long }>
     * @author 杜黎明
     * @date 2022/11/14 09:29:46
     */
    List<Long> getPermissionIdListByRoleIdList(List<Long> roleIdList);
}
