package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.util.Date;

/**
 * @Description: 用户信息详细领域模型
 * @Author: 杜黎明
 * @Date: 2022/09/29 09:25:56
 * @Version: 1.0.0
 */
@Data
@ApiModel("用户信息详情领域模型")
public class BackgroundUserInfo extends BasePO{

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录账号id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 用户头像
     */
    private String profile;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 删除状态(0-未删除,1-已删除)
     */
    @TableLogic
    private Integer isDeleted;
}
