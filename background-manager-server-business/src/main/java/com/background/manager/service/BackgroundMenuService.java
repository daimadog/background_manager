package com.background.manager.service;

import com.background.manager.dto.request.permission.AddPermsRequest;
import com.background.manager.dto.request.permission.ModifyPermsRequest;
import com.background.manager.dto.request.permission.QueryPermsRequest;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.dto.response.permission.PermissionDigestDTO;
import com.background.manager.model.BackgroundMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 用户权限菜单实现接口类
 * @Author: 杜黎明
 * @Date: 2022/09/29 17:49:37
 * @Version: 1.0.0
 */
public interface BackgroundMenuService extends IService<BackgroundMenu> {

    /**
     * Description: 查询所有的接口地址及接口权限标识
     * @return {@link List }<{@link PermissionDTO }>
     * @author 杜黎明
     * @date 2022/09/30 09:13:30
     */
    List<PermissionDTO> getPermissionList();


    /**
     * Description:  根据当前账户的权限标识
     * @param loginId 用户id
     * @return {@link List }<{@link PermissionDTO }>
     * @author 杜黎明
     * @date 2022/09/30 10:28:20
     */
    List<PermissionDTO> getPermissionListById(String loginId);

    /**
     * Description: 权限列表-树形结构展示
     * @param request 请求
     * @return {@link List }<{@link PermissionDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/12 15:08:04
     */
    List<PermissionDigestDTO> listQuery(QueryPermsRequest request);

    /**
     * Description: 新增权限菜单
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/10/12 16:02:46
     */
    String add(AddPermsRequest request);

    /**
     * Description: 修改权限菜单
     * @param request 修改权限菜单请求
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/12 16:53:04
     */
    boolean modify(ModifyPermsRequest request);

    /**
     * Description: 删除权限菜单
     * @param id id
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/12 17:07:52
     */
    boolean delete(Long id);

    BackgroundMenu getPermissionByPermId(Long premId);

    /**
     * Description: 查询permissionIdList下的所以权限列表
     * @param permissionIdList 权限id列表
     * @return {@link List }<{@link PermissionDTO }>
     * @author 杜黎明
     * @date 2022/11/07 13:25:02
     */
    List<PermissionDTO> getPermissionByPermIdList(List<Long> permissionIdList);

    /**
     * Description: 获取所有的权限
     * @return {@link List }<{@link PermissionDTO }>
     * @author 杜黎明
     * @date 2022/11/07 13:33:03
     */
    List<PermissionDTO> getAllPermissionList();

    /**
     * Description: 查询无父节点的权限列表
     *
     * @param id
     * @param permissionIdList 权限id列表
     * @return {@link List }<{@link PermissionDTO }>
     * @author 杜黎明
     * @date 2022/11/07 14:12:17
     */
    Boolean getNoParentPermissionDTO(Long id, List<Long> permissionIdList);

    /**
     * Description: 是否含子节点children
     * @param id
     * @return boolean
     * @author 杜黎明
     * @date 2022/11/07 14:52:16
     */
    boolean existChildren(Long id);

    /**
     * Description: 判断权限是否存在
     * @param parentId 父id
     * @return boolean
     * @author 杜黎明
     * @date 2023/02/09 13:59:07
     */
    boolean existMenu(Long parentId);
}
