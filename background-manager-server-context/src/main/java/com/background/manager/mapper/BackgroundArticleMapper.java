package com.background.manager.mapper;

import com.background.manager.model.BackgroundCmsArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 新闻文章映射器
 * @Author: 杜黎明
 * @Date: 2022/10/25 19:48:59
 * @Version: 1.0.0
 */
@Mapper
public interface BackgroundArticleMapper extends BaseMapper<BackgroundCmsArticle> {
}
