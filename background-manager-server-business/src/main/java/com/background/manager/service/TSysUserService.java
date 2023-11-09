package com.background.manager.service;

import com.background.manager.dto.request.console.getCustomerListRequest;
import com.background.manager.dto.request.user.*;
import com.background.manager.dto.response.user.TSysUserDigestDTO;
import com.background.manager.model.TSysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TSysUserService extends IService<TSysUser> {

    /**
     * Description: 分页查询用户
     * @param request 请求
     * @return {@link IPage }<{@link TSysUserDigestDTO }>
     * @author 杜黎明
     * @date 2022/11/03 16:13:25
     */
    IPage<TSysUserDigestDTO> pageQuery(PageQueryTSysUserRequest request);

    /**
     * Description: 删除用户
     * @param request 请求
     * @return boolean
     * @author 杜黎明
     * @date 2022/11/03 16:13:18
     */
    boolean delete( DeleteUserRequest request);

    /**
     * Description: 通过用户审核
     * @param request 请求
     * @author 杜黎明
     * @date 2023/02/17 14:38:31
     */
    void ActivateAccount(ActivateAccountRequest request);

    /**
     * Description: 拒绝用户审核
     * @param request 请求
     * @author 杜黎明
     * @date 2023/02/17 14:40:04
     */
    void rejectAccount(RejectAccountRequest request);

    /**
     * Description: 列表查询用户请求体
     * @param request 请求
     * @return {@link List }<{@link TSysUserDigestDTO }>
     * @author 杜黎明
     * @date 2023/03/10 16:36:17
     */
    List<TSysUserDigestDTO> listReceptionUser(ListReceptionUserRequest request);

    /**
     * Description: 根据userId获取前台用户
     * @param userId 用户id
     * @return {@link TSysUser }
     * @author 杜黎明
     * @date 2023/03/22 13:38:08
     */
    TSysUser getUserByUserId(String userId);

    /**
     * Description: 根据查询条件查询前台用户
     * @param request 请求
     * @return {@link List }<{@link TSysUser }>
     * @author 杜黎明
     * @date 2023/03/31 14:48:25
     */
    List<TSysUser> getUserByQueryRequest(getCustomerListRequest request);
}
