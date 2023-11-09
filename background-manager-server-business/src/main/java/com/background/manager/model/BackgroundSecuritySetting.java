package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("密码安全设置实体类")
public class BackgroundSecuritySetting {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 密码长度
     */
    private Long passwordLength;
    /**
     * 登录失败次数
     */
    private Long failNum;
    /**
     * 锁定时间
     */
    private Long LockTime;

}
