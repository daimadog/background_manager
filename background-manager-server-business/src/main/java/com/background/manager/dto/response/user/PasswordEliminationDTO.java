package com.background.manager.dto.response.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("常用密码剔除DTO")
public class PasswordEliminationDTO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 密码值
     */
    private String password;
}
