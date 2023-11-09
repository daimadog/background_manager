package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.background.manager.constant.PortalStyleEnum;
import com.background.manager.convert.BackgroundCarouselConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.carousel.AddCarouselRequest;
import com.background.manager.model.dto.request.carousel.ModifyCarouselRequest;
import com.background.manager.model.dto.request.carousel.PageQueryCarouselRequest;
import com.background.manager.model.dto.response.carousel.CarouselDTO;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundCarouselPageMapper;
import com.background.manager.model.BackgroundCarouselPage;
import com.background.manager.model.dto.response.carousel.PortalStyleDTO;
import com.background.manager.service.BackgroundCarouselPageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 轮播页接口实现类
 * @Author: 杜黎明
 * @Date: 2022/11/02 09:30:45
 * @Version: 1.0.0
 */
@Service
public class BackgroundCarouselPageServiceImpl extends ServiceImpl<BackgroundCarouselPageMapper,BackgroundCarouselPage> implements BackgroundCarouselPageService {

    @Resource
    private BackgroundCarouselConvertor backgroundCarouselConvertor;


    @Override
    public IPage<CarouselDTO> pageQuery(PageQueryCarouselRequest request) {
        IPage<BackgroundCarouselPage> page = this.lambdaQuery()
                .eq(StringUtils.isNotBlank(request.getTitle()), BackgroundCarouselPage::getTitle, request.getTitle())
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        Page<CarouselDTO> pages=new Page<>(page.getCurrent(),page.getSize());
        pages.setTotal(page.getTotal());
        pages.setPages(page.getPages());
        pages.setRecords(backgroundCarouselConvertor.toCarouselDTOS(page.getRecords()));
        return pages;
    }

    @Override
    public String add(AddCarouselRequest request) {
        BackgroundCarouselPage carouselPage = this.getOne(new LambdaQueryWrapper<BackgroundCarouselPage>()
                .eq(BackgroundCarouselPage::getTitle, request.getTitle()));
        if (ObjectUtils.isNotEmpty(carouselPage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CAROUSEL_PAGE_TITLE_REPEAT_ERROR);
        }
        BackgroundCarouselPage backgroundCarouselPage = backgroundCarouselConvertor.toBackgroundCarouselPage(request);
        this.save(backgroundCarouselPage);
        return backgroundCarouselPage.getTitle();
    }

    @Override
    public void modify(ModifyCarouselRequest request) {
        BackgroundCarouselPage carouselPage = this.getOne(new LambdaQueryWrapper<BackgroundCarouselPage>()
                .ne(BackgroundCarouselPage::getId,request.getId())
                .eq(BackgroundCarouselPage::getTitle, request.getTitle()));
        if (ObjectUtils.isNotEmpty(carouselPage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CAROUSEL_PAGE_TITLE_REPEAT_ERROR);
        }
        BackgroundCarouselPage backgroundCarouselPage = backgroundCarouselConvertor.toBackgroundCarouselPage(request);
        this.updateById(backgroundCarouselPage);
    }

    @Override
    public void delete(Long id) {
        BackgroundCarouselPage carouselPage = this.getOne(new LambdaQueryWrapper<BackgroundCarouselPage>()
                .eq(BackgroundCarouselPage::getId, id));
        if (ObjectUtils.isEmpty(carouselPage)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CAROUSEL_PAGE_NOT_EXIST_ERROR);
        }
        this.removeById(id);
    }

    @Override
    public List<PortalStyleDTO> listStyle() {
        List<PortalStyleDTO> portalStyleDTOList=new ArrayList<>();
        for (PortalStyleEnum portalStyleEnum:PortalStyleEnum.values()){
            PortalStyleDTO portalStyleDTO=new PortalStyleDTO();
            portalStyleDTO.setNumber(portalStyleEnum.getNumber());
            portalStyleDTO.setDescription(portalStyleEnum.getDescription());
            portalStyleDTOList.add(portalStyleDTO);
        }
        return portalStyleDTOList;
    }

}
