package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Description: 登陆日志领域模型实体类
 * @Author: 杜黎明
 * @Date: 2022/10/13 17:33:30
 * @Version: 1.0.0
 */
@Data
@ApiModel("运营登录日志")
public class BackgroundLoginLog  {
    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Long id;
    /**
     * 登录账号
     */
    private String loginId;
    /**
     * 登录地址
     */
    private String creatorIp;
    /**
     * 登录地点
     */
    private String creatorAddress;
    /**
     * 登录浏览器
     */
    private String browser;
    /**
     * 操作系统
     */
    private String operatingSystem;
    /**
     * 登录状态
     */
    private Integer status;
    /**
     * 登录信息
     */
    private String message;
    /**
     * 登录时间
     */
    @TableField(
            fill = FieldFill.INSERT
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 日志标签(0-运营,1-用户)
     */
    private Integer logFlag;
}
