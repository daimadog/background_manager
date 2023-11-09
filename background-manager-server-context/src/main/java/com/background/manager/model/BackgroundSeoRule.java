package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("SEO配置规则")
public class BackgroundSeoRule extends ContextBasePO{
    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * seo配置规则
     */
    private String seoRule;
}
