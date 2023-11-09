package com.background.manager.mapper;

import com.background.manager.model.BackgroundOperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 操作日志映射器
 * @Author: 杜黎明
 * @Date: 2022/10/17 09:43:29
 * @Version: 1.0.0
 */
@Mapper
public interface BackgroundOperationLogMapper extends BaseMapper<BackgroundOperationLog> {

}
