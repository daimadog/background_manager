package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 运营后台角色领域模型
 * @Author: 杜黎明
 * @Date: 2022/09/29 16:29:45
 * @Version: 1.0.0
 */
@Data
@ApiModel("后台角色")
public class BackgroundRole extends BasePO{
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色英文名称
     */
    private String enName;
    /**
     * 角色状态
     */
    private Integer status;

    /**
     * 角色软删除区分
     */
    @TableLogic
    private Integer isDeleted;

}
