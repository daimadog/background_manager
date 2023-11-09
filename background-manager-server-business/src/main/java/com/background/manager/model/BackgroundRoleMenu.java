package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @Description: 运营后天角色-权限关联实体类
 * @Author: 杜黎明
 * @Date: 2022/09/29 16:51:22
 * @Version: 1.0.0
 */
@Data
@ApiModel(description = "角色-权限关联实体类")
public class BackgroundRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限标识id
     */
    private Long permissionId;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(
            fill = FieldFill.INSERT
    )
    private LocalDateTime createTime;




}
