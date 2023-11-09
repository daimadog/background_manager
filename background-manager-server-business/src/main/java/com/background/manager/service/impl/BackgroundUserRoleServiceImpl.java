package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.background.manager.model.BackgroundUserRole;
import com.background.manager.exception.BadRequestException;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.mapper.BackgroundUserRoleMapper;
import com.background.manager.service.BackgroundUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackgroundUserRoleServiceImpl extends ServiceImpl<BackgroundUserRoleMapper, BackgroundUserRole> implements BackgroundUserRoleService {

    @Override
    public List<Long> getRoleIds(Long userId) {
        List<Long> roleIdList=new ArrayList<>();
        List<BackgroundUserRole> userRoles = this.list(new LambdaQueryWrapper<BackgroundUserRole>()
                .eq(BackgroundUserRole::getUserId, userId));
        if (CollectionUtil.isNotEmpty(userRoles)){
            for (BackgroundUserRole backgroundUserRole:userRoles){
                roleIdList.add(backgroundUserRole.getRoleId());
            }
        }
        return roleIdList;
    }

    @Override
    public void add(BackgroundUserRole backgroundUserRole) {
        boolean result = this.save(backgroundUserRole);
        if (!result) {
            throw new BadRequestException(ResultCodeEnum.CREATE_FAIL);
        }
    }

    @Override
    public void clean(Long userId) {
        List<BackgroundUserRole> backgroundUserRoles = this.list(new LambdaQueryWrapper<BackgroundUserRole>()
                .eq(BackgroundUserRole::getUserId, userId));
        if (CollectionUtil.isNotEmpty(backgroundUserRoles)){
            for (BackgroundUserRole backgroundUserRole:backgroundUserRoles){
                this.remove(new LambdaQueryWrapper<BackgroundUserRole>()
                        .eq(BackgroundUserRole::getUserId,backgroundUserRole.getUserId())
                        .eq(BackgroundUserRole::getRoleId,backgroundUserRole.getRoleId()));
            }
        }
    }


}
