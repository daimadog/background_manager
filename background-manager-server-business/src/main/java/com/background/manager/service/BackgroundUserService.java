package com.background.manager.service;

import com.background.manager.dto.request.user.*;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.user.BackgroundUserInfoDTO;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.model.BackgroundUser;
import com.background.manager.model.BackgroundUserInfo;
import com.background.manager.response.ApiResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 用户管理实现接口类
 * @Author: 杜黎明
 * @Date: 2022/09/30 14:33:56
 * @Version: 1.0.0
 */
public interface BackgroundUserService  extends IService<BackgroundUser> {


    /**
     * Description: 用户注册
     * @param request 用户注册请求体
     * @return {@link String } -用户登录账号
     * @author 杜黎明
     * @date 2022/10/08 15:26:44
     */
    String register(BackgroundUserRegisterRequest request);

    /**
     * Description: 用户登录
     * @param request 请求
     * @return {@link BackgroundUserInfoDTO }
     * @author 杜黎明
     * @date 2022/10/08 16:36:20
     */
    LoginUserInfoDTO login(BackgroundUserLoginRequest request, HttpServletRequest httpServletRequest);

    /**
     * Description: 用户注销
     * @author 杜黎明
     * @date 2022/10/08 17:13:35
     */
    void logout();

    /**
     * Description: 冻结用户
     * @param id 用户id
     * @return {@link Boolean }
     * @author 杜黎明
     * @date 2022/10/11 09:34:35
     */
    Boolean freeze(Long id);

    /**
     * Description: 解冻用户
     * @param id 用户id
     * @return {@link Boolean }
     * @author 杜黎明
     * @date 2022/10/11 09:55:15
     */
    Boolean unFreeze(Long id);

    /**
     * Description:删除用户
     * @param id 用户id
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/11 10:13:10
     */
    Boolean delete(Long id);

    /**
     * Description: 获取当前用户的角色列表
     * @param id 用户id
     * @return {@link List }<{@link RoleDTO }>
     * @author 杜黎明
     * @date 2022/10/11 10:22:33
     */
    List<RoleDTO> getRoles(Long id);

    /**
     * Description:修改密码
     * @param request 修改密码请求体
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/11 15:00:00
     */
    boolean modifyPassword(ModifyUserPasswordRequest request);

    /**
     * Description: 获取当前登录用户
     * @return {@link BackgroundUser }
     * @author 杜黎明
     * @date 2022/10/11 15:25:57
     */
    BackgroundUser getUser();

    /**
     * Description: 获取当前登录用户的详细信息
     * @return {@link BackgroundUserInfo }
     * @author 杜黎明
     * @date 2022/10/11 15:38:47
     */
    BackgroundUserInfo getUserInfo();

    /**
     * Description: 设置角色
     * @param userId 用户id
     * @param roleId 角色id
     * @author 杜黎明
     * @date 2023/01/13 16:53:59
     */
    void assignRole(Long userId,List<Long> roleId);

    /**
     * Description: 添加运营端用户
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2023/01/13 15:06:10
     */
    String add(AddUserRequest request);

    /**
     * Description: 编辑运营端用户
     * @param request 请求
     * @author 杜黎明
     * @date 2023/01/13 17:12:28
     */
    void edit(ModifyUserRequest request);

    /**
     * Description: 根据userId获取用户对象
     * @param userId 用户id
     * @return {@link BackgroundUser }
     * @author 杜黎明
     * @date 2023/02/07 17:35:39
     */
    BackgroundUser getUserByUserId(Long userId);

    /**
     * Description: 检查是否登录
     * @return boolean
     * @author 杜黎明
     * @date 2023/03/08 16:36:25
     */
    boolean checkLogin();
}
