package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 后台用户模型
 * @Author: 杜黎明
 * @Date: 2022/09/28 17:16:55
 * @Version: 1.0.0
 */
@Data
@ApiModel("后台用户")
public class BackgroundUser extends BasePO{
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户登录账号
     */
    private String loginId;
    /**
     * 用户登录密码
     */
    private String password;
    /**
     * 用户状态(0-正常,1-冻结)
     */
    private Integer status;
    /**
     * 删除状态(0-未删除,1-已删除)
     */
    @TableLogic
    private Integer isDeleted;
    /**
     * 管理员标识(0-普通运营人员，1-管理员)
     */
    private Integer administratorFlag;
    /**
     *  登录失败次数
     */
    private Integer loginFailNum;
}
