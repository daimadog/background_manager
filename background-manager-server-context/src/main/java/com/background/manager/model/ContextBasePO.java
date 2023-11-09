package com.background.manager.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description: 基础实体领域模型
 * @Author: 杜黎明
 * @Date: 2022/10/26 19:34:45
 * @Version: 1.0.0
 */
@Data
@ApiModel("基础领域模型")
public class ContextBasePO implements Serializable {
    /**
     * 发布时间
     */
    private LocalDateTime createTime;
    /**
     * 发布人
     */
    private String creator;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 更新人
     */
    private String modifier;
}
