package com.background.manager.dto.response.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("用户登录限制配置DTO")
public class UserLoginConfigurationDTO {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 登录失败次数
     */
    private Integer loginFailNum;
    /**
     * 封禁锁定时间
     */
    private Integer lockTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private LocalDateTime creatorTime;
    /**
     * 更新人
     */
    private String modifier;
    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;
}
