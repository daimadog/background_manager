package com.background.manager.mapper;

import com.background.manager.model.BackgroundCmsColumn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 栏目管理映射器
 * @Author: 杜黎明
 * @Date: 2022/10/26 10:36:19
 * @Version: 1.0.0
 */
public interface BackgroundColumnMapper extends BaseMapper<BackgroundCmsColumn> {

    @Select({"select * from background_cms_column c where c.weight<#{weight} order by c.weight DESC LIMIT 0,1"})
    BackgroundCmsColumn selectPreviousColumn(Long weight);

    @Select({"select * from background_cms_column c where c.weight>#{weight} order by c.weight  LIMIT 0,1"})
    BackgroundCmsColumn selectNextColumn(Long weight);
}
