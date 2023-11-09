package com.background.manager.service;

import com.background.manager.model.BackgroundUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 用户-角色关联实现接口类
 * @Author: 杜黎明
 * @Date: 2022/09/30 15:04:54
 * @Version: 1.0.0
 */
public interface BackgroundUserRoleService  extends IService<BackgroundUserRole> {

    /**
     * Description: 查询当前用户的所有角色id
     * @param userId 用户id
     * @return {@link List }<{@link Long }>
     * @author 杜黎明
     * @date 2022/09/30 15:05:45
     */
    List<Long> getRoleIds(Long userId);

    /**
     * Description: 新增用户-角色关联体
     * @param backgroundUserRole 后台用户角色
     * @author 杜黎明
     * @date 2022/10/11 10:53:05
     */
    void add(BackgroundUserRole backgroundUserRole);

    /**
     * Description: 清除用户原先的角色
     * @param userId 用户id
     * @author 杜黎明
     * @date 2022/10/13 10:12:32
     */
    void clean(Long userId);
}
