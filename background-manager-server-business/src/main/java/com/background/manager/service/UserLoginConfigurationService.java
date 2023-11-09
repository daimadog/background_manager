package com.background.manager.service;

import com.background.manager.dto.request.user.modifyUserLoginConfigurationRequest;
import com.background.manager.dto.response.user.UserLoginConfigurationDTO;
import com.background.manager.model.UserLoginConfiguration;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserLoginConfigurationService extends IService<UserLoginConfiguration> {

    /**
     * Description: 修改用户登录配置
     * @param request 请求
     * @author 杜黎明
     * @date 2023/04/03 16:58:26
     */
    void modifyUserLoginConfiguration(modifyUserLoginConfigurationRequest request);

    /**
     * Description: 获取用户登录限制配置
     * @return {@link UserLoginConfigurationDTO }
     * @author 杜黎明
     * @date 2023/04/03 17:07:25
     */
    UserLoginConfigurationDTO getUserLoginConfiguration();
}
