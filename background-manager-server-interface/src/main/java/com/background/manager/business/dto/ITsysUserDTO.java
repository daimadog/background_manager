package com.background.manager.business.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ITsysUserDTO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 联系邮箱
     */
    private String contactEmail;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLogin;
    /**
     * 注册时间
     */
    private LocalDateTime registerTime;
    /**
     * 状态
     */
    private Integer applyStatus;
    /**
     * 星云用户id
     */
    private String zsUserId;

    /**
     * 星云用户编号
     */
    private String zsOrgSid;
}
