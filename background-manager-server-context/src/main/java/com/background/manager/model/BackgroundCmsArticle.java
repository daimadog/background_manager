package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @Description: 新闻文章实体领域模型
 * @Author: 杜黎明
 * @Date: 2022/10/25 17:32:21
 * @Version: 1.0.0
 */
@Data
@ApiModel("新闻文章实体类")
public class BackgroundCmsArticle  extends ContextBasePO{
    /**
     * 文章编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章缩略图
     */
    private String imgUrl;
    /**
     * 文章描述
     */
    private String description;
    /**
     * 是否原创(0-转载,1-原创)
     */
    private Integer isOriginal;
    /**
     * 外部链接
     */
    private String href;
    /**
     * 来源
     */
    private String source;
    /**
     * 文章状态(0-正常，1-冻结)
     */
    private Integer status;
    /**
     * 是否置顶
     */
    private Integer isTop;
    /**
     * 所属栏目ID
     */
    private Long columnId;
    /**
     * 新闻发布时间
     */
    private String articleTime;

}
