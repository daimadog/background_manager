package com.background.manager.mapper;

import com.background.manager.model.BackgroundUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.lang.annotation.Target;

/**
 * @Description: 用户映射器
 * @Author: 杜黎明
 * @Date: 2022/09/29 17:02:17
 * @Version: 1.0.0
 */
@Mapper
public interface BackgroundUserMapper extends BaseMapper<BackgroundUser> {

}
