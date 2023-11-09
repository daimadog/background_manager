package com.background.manager.business.dto;

import lombok.Data;

@Data
public class IBackgroundUserDTO {
    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户登录账号
     */
    private String loginId;
    /**
     * 用户状态(0-正常,1-冻结)
     */
    private Integer status;
    /**
     * 管理员标识
     */
    private Integer administratorFlag;
    /**
     *  用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String phone;

}
