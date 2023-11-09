package com.background.manager.mapper;

import com.background.manager.model.BackgroundUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 用户信息映射器
 * @Author: 杜黎明
 * @Date: 2022/09/29 17:04:47
 * @Version: 1.0.0
 */
@Mapper
public interface BackgroundUserInfoMapper extends BaseMapper<BackgroundUserInfo> {
}
