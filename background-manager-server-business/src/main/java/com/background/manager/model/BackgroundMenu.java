package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 运营后台用户权限领域模型
 * @Author: 杜黎明
 * @Date: 2022/09/29 16:40:33
 * @Version: 1.0.0
 */
@Data
@ApiModel("用户权限实体类")
public class BackgroundMenu extends  BasePO {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 路由地址
     */
    private String path;

    /**
     *父菜单id
     */
    private Long parentId;

    /**
     * 权限类型(1-目录, 2-菜单,3-按钮,4-按钮)
     */
    private Integer type;

    /**
     *  权限状态(0-启用,1-停用)
     */
    private Integer status;

    /**
     * 菜单图标
     */
    private  String icon;

    /**
     * 权限标识
     */
    private String perms;
    /**
     * 删除状态(0-未删除,1-已删除)
     */
    @TableLogic
    private Integer isDeleted;

}
