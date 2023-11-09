package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description: 运营后台用户-角色关联实体类
 * @Author: 杜黎明
 * @Date: 2022/09/29 16:37:08
 * @Version: 1.0.0
 */
@Data
@ApiModel(description = "用户-角色关联实体类")
public class BackgroundUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户角色id
     */
    @TableField("role_id")
    private Long roleId;
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
