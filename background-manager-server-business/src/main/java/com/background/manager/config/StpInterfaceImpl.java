package com.background.manager.config;

import cn.dev33.satoken.stp.StpInterface;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.service.BackgroundMenuService;
import com.background.manager.service.BackgroundRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 自定义权限集合接口
 * @Author: 杜黎明
 * @Date: 2022/09/29 17:44:07
 * @Version: 1.0.0
 */
@Component
@AllArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final BackgroundRoleService backgroundRoleService;

    private final BackgroundMenuService backgroundMenuService;

    /**
     * Description: 返回指定账号id所拥有的权限码集合
     * @param loginId   用户名
     * @param loginType 登录设备
     * @return {@link List }<{@link String }>
     * @author 杜黎明
     * @date 2022/09/29 17:46:19
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String username = (String) loginId;
        List<PermissionDTO> permissionDTOList=backgroundMenuService.getPermissionListById(username);
        List<String> permissionList=new ArrayList<>();
        permissionDTOList.forEach(permission -> {
            permissionList.add(permission.getPerms());
        });
        return permissionList;
    }

    /**
     * Description: 返回指定账号id所拥有的角色集合
     * @param loginId   用户名
     * @param loginType 登录设备
     * @return {@link List }<{@link String }>
     * @author 杜黎明
     * @date 2022/09/29 17:46:45
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String username = (String) loginId;
        List<RoleDTO> roleDTOList=backgroundRoleService.getRoleByLoginId(username);
        List<String> roleList=new ArrayList<>();
        roleDTOList.forEach(role ->{
            roleList.add(role.getRoleName());
        });
        return roleList;
    }
}
