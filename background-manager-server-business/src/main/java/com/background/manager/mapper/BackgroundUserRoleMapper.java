package com.background.manager.mapper;

import com.background.manager.model.BackgroundUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 用户-角色关联表映射器
 * @Author: 杜黎明
 * @Date: 2022/09/29 17:05:55
 * @Version: 1.0.0
 */
@Mapper
public interface BackgroundUserRoleMapper extends BaseMapper<BackgroundUserRole> {
}
