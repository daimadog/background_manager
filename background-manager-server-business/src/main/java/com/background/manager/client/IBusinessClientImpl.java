package com.background.manager.client;

import cn.hutool.core.util.ObjectUtil;
import com.background.manager.business.IBusinessClient;
import com.background.manager.business.dto.IBackgroundUserDTO;
import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.convert.BackgroundUserConvertor;
import com.background.manager.convert.TSysUserConvertor;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.model.BackgroundUser;
import com.background.manager.model.TSysUser;
import com.background.manager.service.BackgroundUserInfoService;
import com.background.manager.service.BackgroundUserService;
import com.background.manager.service.TSysUserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * @Description: 内部接口实现类
 * @Author: 杜黎明
 * @Date: 2023/02/27 10:33:48
 * @Version: 1.0.0
 */
@Service
public class IBusinessClientImpl implements IBusinessClient {

    @Resource
    private TSysUserService tSysUserService;

    @Resource
    private TSysUserConvertor tSysUserConvertor;

    @Resource
    private BackgroundUserService backgroundUserService;

    @Resource
    private BackgroundUserInfoService backgroundUserInfoService;

    @Resource
    private BackgroundUserConvertor backgroundUserConvertor;

    @Override
    public ITsysUserDTO getTsysUserByAccountId(String accountId) {
        TSysUser tSysUser = tSysUserService.getOne(
                new LambdaQueryWrapper<TSysUser>()
                        .eq(TSysUser::getUserId,accountId)
        );
        return  tSysUserConvertor.toITsysUserDTO(tSysUser);
    }

    @Override
    @DS("master")
    public IBackgroundUserDTO getUserByLoginId(String loginId) {
        BackgroundUser backgroundUser = backgroundUserService.getOne(new LambdaQueryWrapper<BackgroundUser>()
                .eq(BackgroundUser::getLoginId, loginId));
        if (ObjectUtil.isNull(backgroundUser)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        IBackgroundUserDTO backgroundUserDTO = backgroundUserConvertor.toIBackgroundUserDTO(backgroundUser);
        LoginUserInfoDTO backgroundUserInfo = backgroundUserInfoService.findUserInfoByLoginId(loginId);
        backgroundUserDTO.setUserName(backgroundUserInfo.getUsername());
        backgroundUserDTO.setPhone(backgroundUserInfo.getPhone());
        return backgroundUserDTO;
    }

}
