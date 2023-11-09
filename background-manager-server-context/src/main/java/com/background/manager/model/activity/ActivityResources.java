package com.background.manager.model.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("活动—计算资源实体领域模型")
public class ActivityResources extends Activity {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源描述
     */
    private String description;
    /**
     * 资源缩略图
     */
    private String imageUrl;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 现价
     */
    private BigDecimal preferentialPrice;
    /**
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 外部链接
     */
    private String contentUrl;
    /**
     * 活动编号
     */
    private Long activityId;
}
