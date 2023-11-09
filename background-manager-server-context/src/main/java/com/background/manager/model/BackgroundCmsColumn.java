package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 栏目管理实体领域模型
 * @Author: 杜黎明
 * @Date: 2022/10/26 10:16:57
 * @Version: 1.0.0
 */
@Data
@ApiModel("栏目管理")
public class BackgroundCmsColumn extends ContextBasePO{
    /**
     * 栏目主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 栏目名称
     */
    private String name;
    /**
     * 栏目英文名
     */
    private  String enName;
    /**
     * 上级栏目Id
     */
    private  Long parentId;
    /**
     * 栏目描述
     */
    private  String description;
    /**
     * 模板链接
     */
    private  String templatePath;
    /**
     * 状态（0-正常，1-冻结）
     */
    private Integer status;
    /**
     * 栏目路径地址
     */
    private String path;
    /**
     * 是否展示（0-展示,1-不展示）
     */
    private Integer onTap;
    /**
     * 菜单栏目
     */
    private Long weight;

}
