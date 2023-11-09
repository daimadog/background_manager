package com.background.manager.business;

import com.background.manager.business.dto.IBackgroundUserDTO;
import com.background.manager.business.dto.ITsysUserDTO;

/**
 * @Description: 用户信息内部接口类
 * @Author: 杜黎明
 * @Date: 2023/02/27 10:11:23
 * @Version: 1.0.0
 */
public interface IBusinessClient {

    /**
     * Description: 根据账户ID获取用户信息
     * @param accountId 帐户id
     * @return {@link ITsysUserDTO }
     * @author 杜黎明
     * @date 2023/02/27 10:32:58
     */
    ITsysUserDTO getTsysUserByAccountId(String accountId);


    /**
     * Description: 根据登录账号获取后台用户
     * @param loginId 登录id
     * @return {@link IBackgroundUserDTO }
     * @author 杜黎明
     * @date 2023/03/10 17:01:04
     */
    IBackgroundUserDTO getUserByLoginId(String loginId);


}
