package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: 离线登记实体类
 * @Author: 杜黎明
 * @Date: 2022/11/03 08:57:59
 * @Version: 1.0.0
 */
@Data
@ApiModel("离线登记实体领域模型")
public class BackgroundOfflineMessage {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 公司名称/学校名称
     */
    private String company;
    /**
     * 职位
     */
    private String  status;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 离线登记内容
     */
    private String messageContent;
    /**
     * 处理状态(0-未处理，1-已处理)
     */
    private Integer  processState;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
