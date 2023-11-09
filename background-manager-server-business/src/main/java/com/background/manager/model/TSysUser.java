package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("从数据库用户实体模型")
public class TSysUser {

    @TableId(value = "user_id", type = IdType.AUTO)
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
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
     * 删除状态(0-未删除,1-已删除)
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 星云用户id
     */
    private String zsUserId;

    /**
     * 星云用户编号
     */
    private String zsOrgSid;
    /**
     * 登录失败次数
     */
    private Integer loginFailNum;
    /**
     * 锁定时间
     */
    private LocalDateTime lockTime;
    /**
     *
     */
    private Integer accountNonLockedFlag;
    /**
     * 超算账户Id
     */
    private String csAccountId;
    /**
     * 超算用户组Id
     */
    private String csGroupId;
    /**
     * 超算区域组Id
     */
    private String csClusterId;
}
