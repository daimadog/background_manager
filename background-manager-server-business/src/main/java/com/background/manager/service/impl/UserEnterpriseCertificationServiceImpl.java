package com.background.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.TSysUserConvertor;
import com.background.manager.dto.request.user.PageQueryUserEnterpriseCertificationRequest;
import com.background.manager.dto.request.user.applyUserEnterpriseCertificationRequest;
import com.background.manager.dto.response.user.UserEnterpriseCertificationDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.UserEnterpriseCertificationMapper;
import com.background.manager.model.TSysUser;
import com.background.manager.model.UserEnterpriseCertification;
import com.background.manager.service.TSysUserService;
import com.background.manager.service.UserEnterpriseCertificationService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户企业认证接口实现类
 * @Author: 杜黎明
 * @Date: 2023/03/22 13:07:31
 * @Version: 1.0.0
 */
@Service
public class UserEnterpriseCertificationServiceImpl extends ServiceImpl<UserEnterpriseCertificationMapper, UserEnterpriseCertification> implements UserEnterpriseCertificationService {

    @Resource
    private TSysUserService tSysUserService;
    @Resource
    private TSysUserConvertor tSysUserConvertor;

    @Override
    public void apply(applyUserEnterpriseCertificationRequest request) {
        UserEnterpriseCertification certification = this.getOne(new LambdaUpdateWrapper<UserEnterpriseCertification>()
                .eq(UserEnterpriseCertification::getId, request.getId()));
        if (ObjectUtil.isNull(certification)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CERTIFICATION_ENTITY_NOT_EXIT_ERROR);
        }
        TSysUser user=tSysUserService.getUserByUserId(certification.getUserId());
        if (ObjectUtil.isNull(user)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.update(new LambdaUpdateWrapper<UserEnterpriseCertification>()
                .eq(UserEnterpriseCertification::getId, request.getId())
                .set(UserEnterpriseCertification::getCertificationStatus, request.getCertificationStatus())
        );
    }

    @Override
    public IPage<UserEnterpriseCertificationDTO> listUserEnterpriseCertification(PageQueryUserEnterpriseCertificationRequest request) {
        Page<UserEnterpriseCertification> page = this.lambdaQuery()
                .eq(ObjectUtil.isNotNull(request.getCertificationStatus()), UserEnterpriseCertification::getCertificationStatus, request.getCertificationStatus())
                .eq(ObjectUtil.isNotNull(request.getUserId()), UserEnterpriseCertification::getUserId, request.getUserId())
                .eq(StringUtils.isNotBlank(request.getCompanyName()), UserEnterpriseCertification::getCompanyName, request.getCompanyName())
                .orderByAsc(UserEnterpriseCertification::getCertificationStatus)
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtils.isEmpty(page.getRecords())){
            return new Page<>();
        }
        List<UserEnterpriseCertificationDTO> userEnterpriseCertificationDTOList=new ArrayList<>();
        for (UserEnterpriseCertification amp:page.getRecords()){
            UserEnterpriseCertificationDTO dto=tSysUserConvertor.toUserEnterpriseCertificationDTO(amp);
            TSysUser user = tSysUserService.getUserByUserId(amp.getUserId());
            if (ObjectUtil.isNotNull(user)){
                dto.setUsername(user.getUsername());
            }
            userEnterpriseCertificationDTOList.add(dto);
        }
        Page<UserEnterpriseCertificationDTO> pages=new Page<>();
        pages.setPages(page.getPages())
                .setCurrent(page.getCurrent())
                .setTotal(page.getTotal())
                .setSize(page.getSize())
                .setRecords(userEnterpriseCertificationDTOList);
        return pages;
    }

}
