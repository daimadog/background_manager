package com.background.manager.service;

import com.background.manager.dto.request.user.ModifyUserInfoRequest;
import com.background.manager.dto.request.user.PageQueryUserInfoRequest;
import com.background.manager.dto.response.user.BackgroundUserInfoDTO;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.dto.response.user.UserInfoDTO;
import com.background.manager.dto.response.user.UserInfoDigestDTO;
import com.background.manager.model.BackgroundUserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BackgroundUserInfoService extends IService<BackgroundUserInfo> {
    /**
     * Description: 分页查询用户信息
     * @param request
     * @return {@link IPage}<{@link UserInfoDigestDTO}>
     * @author 杜黎明
     * @date 2022/10/10 11:38:12
     */
    IPage<UserInfoDigestDTO> pageQuery(PageQueryUserInfoRequest request);

    /**
     * Description: 冻结用户
     * @param id 用户id
     * @author 杜黎明
     * @date 2022/10/11 09:48:16
     */
    void freeze(Long id);

    /**
     * Description: 解冻用户
     * @param id 用户id
     * @author 杜黎明
     * @date 2022/10/11 09:57:31
     */
    void unFreeze(Long id);

    /**
     * Description:删除用户信息
     * @param id id
     * @author 杜黎明
     * @date 2022/10/11 10:17:13
     */
    void delete(Long id);

    /**
     * Description: 查询当前用户信息
     * @param id 用户id
     * @return {@link UserInfoDTO }
     * @author 杜黎明
     * @date 2022/10/11 14:29:37
     */
    UserInfoDTO findUserInfoById(Long id);

    /**
     * Description: 修改用户个人信息
     * @param request 修改用户个人信息请求体
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/11 15:14:41
     */
    boolean modify(ModifyUserInfoRequest request);

    /**
     * Description: 查询当前登录用户的用户信息
     * @param loginId 登录id
     * @return {@link BackgroundUserInfoDTO }
     * @author 杜黎明
     * @date 2023/01/12 11:29:05
     */
    LoginUserInfoDTO findUserInfoByLoginId(String loginId);
}
