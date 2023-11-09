package com.background.manager.service;

import com.background.manager.dto.request.role.AddRoleRequest;
import com.background.manager.dto.request.role.AssignPermsRequest;
import com.background.manager.dto.request.role.ModifyRoleRequest;
import com.background.manager.dto.request.role.PageQueryRoleRequest;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.role.RoleDetailDTO;
import com.background.manager.dto.response.role.RoleDigestDTO;
import com.background.manager.model.BackgroundRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 用户角色实现接口类
 * @Author: 杜黎明
 * @Date: 2022/09/30 13:27:53
 * @Version: 1.0.0
 */
public interface BackgroundRoleService  extends IService<BackgroundRole> {
    /**
     * Description: 查询当前用户配置的角色
     * @param loginId 用户账号
     * @return {@link List }<{@link RoleDTO }>
     * @author 杜黎明
     * @date 2022/09/30 13:29:19
     */
    List<RoleDTO> getRoleByLoginId(String loginId);

    /**
     * Description: 查看所有的角色信息列表
     * @return {@link List }<{@link RoleDTO }>
     * @author 杜黎明
     * @date 2022/10/10 14:09:17
     */
    List<RoleDTO> queryAllRole();

    /**
     * Description:获取当前角色
     * @param roleId 角色id
     * @return {@link BackgroundRole }
     * @author 杜黎明
     * @date 2022/10/11 10:37:18
     */
    BackgroundRole getRoleByRoleId(Long roleId);

    /**
     * Description: 分页查询角色列表
     * @param request 请求
     * @return {@link IPage }<{@link RoleDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/11 17:07:11
     */
    IPage<RoleDigestDTO> pageQuery(PageQueryRoleRequest request);

    /**
     * Description: 新增角色
     * @param request 新增角色请求体
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/10/11 17:20:21
     */
    String add(AddRoleRequest request);

    /**
     * Description: 删除角色
     * @param id 角色id
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/12 09:10:24
     */
    boolean delete(Long id);

    /**
     * Description: 编辑角色
     * @param request 编辑角色请求体
     * @author 杜黎明
     * @date 2022/10/12 09:41:20
     */
    void modify(ModifyRoleRequest request);

    /**
     * Description: 设置权限
     * @param request 请求
     * @author 杜黎明
     * @date 2022/10/12 10:02:00
     */
    void assignPerms(AssignPermsRequest request);

    /**
     * Description: 查看角色详情
     * @param id 角色id
     * @return {@link RoleDetailDTO }
     * @author 杜黎明
     * @date 2022/10/12 10:15:14
     */
    RoleDetailDTO getRoleDetail(Long id);

    /**
     * Description: 查询roleIds列表中的所有角色列表
     * @param roleIds
     * @return {@link BackgroundRole }
     * @author 杜黎明
     * @date 2022/11/07 10:29:58
     */
    List<RoleDTO>  getRoleByRoleIds(List<Long> roleIds);

    /**
     * Description: 根据用户登录id获取对应的角色权限
     * @param userId 用户id
     * @return {@link List }<{@link RoleDTO }>
     * @author 杜黎明
     * @date 2023/01/12 13:55:35
     */
    List<RoleDTO> findRoleByUserId(Long userId);

    /**
     * Description: 校验角色是否都存在
     * @param roleId 角色id
     * @return boolean
     * @author 杜黎明
     * @date 2023/01/13 17:28:22
     */
    boolean vaildRole(List<Long> roleId);
}
