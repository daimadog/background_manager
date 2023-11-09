package com.background.manager.mapper;

import com.background.manager.model.BackgroundLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 登录日志映射器
 * @Author: 杜黎明
 * @Date: 2022/10/13 17:56:46
 * @Version: 1.0.0
 */
@Mapper
public interface BackgroundLoginLogMapper extends BaseMapper<BackgroundLoginLog> {
}
