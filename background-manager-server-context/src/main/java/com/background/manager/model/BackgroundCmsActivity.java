package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 活动领域模型
 * @Author: 杜黎明
 * @Date: 2022/10/26 19:31:16
 * @Version: 1.0.0
 */
@Data
@ApiModel("活动实体类")
public class BackgroundCmsActivity extends ContextBasePO {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 活动背景图
     */
    private String imgUrl;
    /**
     * 活动描述
     */
    private String description;
    /**
     * 所属栏目ID（0-直播，1-计算资源，2-峰会）
     */
    private Long columnId;
    /**
     * 状态(0-正常，1-冻结)
     */
    private Integer status;
    /**
     * 是否原创(0-转载,1-原创)
     */
    private Integer isOriginal;
    /**
     * 外部链接
     */
    private String contentUrl;
    /**
     * 是否置顶
     */
    private Integer isTop;
    /**
     * 活动类型（0-线上活动，1-线下活动）
     */
    private Integer type;
    /**
     * 活动内容
     */
    private String content;

}
