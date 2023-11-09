package com.background.manager.mapper;

import com.background.manager.model.BackgroundCmsActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 活动管理映射器
 * @Author: 杜黎明
 * @Date: 2022/10/26 19:42:42
 * @Version: 1.0.0
 */
@Mapper
public interface BackgroundActivityMapper extends BaseMapper<BackgroundCmsActivity> {

}
