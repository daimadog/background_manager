package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 轮播页实体类
 * @Author: 杜黎明
 * @Date: 2022/11/02 09:14:53
 * @Version: 1.0.0
 */
@Data
@ApiModel("轮播页实体领域模型")
public class BackgroundCarouselPage {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 轮播页标题
     */
    private String title;
    /**
     * 轮播页文字内容
     */
    private String content;
    /**
     * 轮播页图片地址
     */
    private String imageUrl;
    /**
     * 外部链接地址
     */
    private String externalUrl;

}
