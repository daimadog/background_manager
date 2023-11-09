package com.background.manager.model.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("活动—峰会论坛实体领域模型")
public class ActivityText extends Activity {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 内容
     */
    private String content;
    /**
     * 关键词
     */
    private String keywords;
    /**
     * 是否原创
     */
    private Integer isOriginal;
    /**
     * 外部连接
     */
    private String href;
    /**
     * 来源
     */
    private String source;
    /**
     * 活动编号
     */
    private Long activityId;
}
