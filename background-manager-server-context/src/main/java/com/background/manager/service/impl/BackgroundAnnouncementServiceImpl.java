package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.BackgroundAnnouncementConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundAnnouncementMapper;
import com.background.manager.model.BackgroundAnnouncement;
import com.background.manager.model.dto.request.announcement.AddAnnouncementRequest;
import com.background.manager.model.dto.request.announcement.ModifyAnnouncementRequest;
import com.background.manager.model.dto.request.announcement.PageQueryAnnouncementRequest;
import com.background.manager.model.dto.response.announcement.BackgroundAnnouncementDigestDTO;
import com.background.manager.service.BackgroundAnnouncementService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Description: 公告管理接口实现类
 * @Author: 杜黎明
 * @Date: 2023/02/23 13:52:42
 * @Version: 1.0.0
 */
@Service
public class BackgroundAnnouncementServiceImpl  extends ServiceImpl<BackgroundAnnouncementMapper, BackgroundAnnouncement> implements BackgroundAnnouncementService {

    @Resource
    private BackgroundAnnouncementConvertor backgroundAnnouncementConvertor;


    @Override
    public String add(AddAnnouncementRequest request) {
        BackgroundAnnouncement backgroundAnnouncement = this.getOne(
                new LambdaQueryWrapper<BackgroundAnnouncement>()
                        .eq(BackgroundAnnouncement::getTitle, request.getTitle())
        );
        if (ObjectUtil.isNotNull(backgroundAnnouncement)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ANNOUNCEMENT_TITLE_REPEAT_ERROR);
        }
        BackgroundAnnouncement addBackgroundAnnouncement=backgroundAnnouncementConvertor.toBackgroundAnnouncement(request);
        if (StpUtil.isLogin()){
            addBackgroundAnnouncement.setCreator(StpUtil.getLoginIdAsString());
            addBackgroundAnnouncement.setModifier(StpUtil.getLoginIdAsString());
        }
        addBackgroundAnnouncement.setCreateTime(LocalDateTime.now());
        addBackgroundAnnouncement.setUpdateTime(LocalDateTime.now());
        this.save(addBackgroundAnnouncement);
        return addBackgroundAnnouncement.getTitle();
    }

    @Override
    public IPage<BackgroundAnnouncementDigestDTO> pageQuery(PageQueryAnnouncementRequest request) {
        Page<BackgroundAnnouncement> page = this.lambdaQuery()
                .eq(ObjectUtils.isNotEmpty(request.getType()), BackgroundAnnouncement::getType, request.getType())
                .eq(ObjectUtils.isNotEmpty(request.getApplyStatus()), BackgroundAnnouncement::getApplyStatus, request.getApplyStatus())
                .like(StringUtils.isNotBlank(request.getTitle()), BackgroundAnnouncement::getTitle, request.getTitle())
                .eq(ObjectUtils.isNotEmpty(request.getAnnouncementTime()), BackgroundAnnouncement::getAnnouncementTime, request.getAnnouncementTime())
                .orderByDesc(BackgroundAnnouncement::getAnnouncementTime)
                .page(new Page<>(request.getPage(), request.getSize()));
        IPage<BackgroundAnnouncementDigestDTO> pages=new Page<>();
        pages.setPages(page.getPages())
                .setSize(page.getSize())
                .setTotal(page.getTotal())
                .setCurrent(page.getCurrent())
                .setRecords(backgroundAnnouncementConvertor.toBackgroundAnnouncementDigestDTOs(page.getRecords()));
        return pages;
    }

    @Override
    public void edit(ModifyAnnouncementRequest request) {
        BackgroundAnnouncement modifyBackgroundAnnouncement = this.getOne(
                new LambdaQueryWrapper<BackgroundAnnouncement>()
                        .eq(BackgroundAnnouncement::getId, request.getId())
        );
        if (ObjectUtil.isNull(modifyBackgroundAnnouncement)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        modifyBackgroundAnnouncement.setApplyStatus(request.getApplyStatus());
        this.updateById(modifyBackgroundAnnouncement);
    }

    @Override
    public void delete(Long id) {
        BackgroundAnnouncement backgroundAnnouncement = this.getOne(
                new LambdaQueryWrapper<BackgroundAnnouncement>()
                        .eq(BackgroundAnnouncement::getId,id)
        );
        if (ObjectUtil.isNull(backgroundAnnouncement)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.removeById(backgroundAnnouncement);
    }

}
