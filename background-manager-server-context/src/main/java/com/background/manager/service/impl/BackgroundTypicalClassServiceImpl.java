package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.HomeConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.home.AddTypicalClassRequest;
import com.background.manager.model.dto.request.home.ModifyTypicalClassRequest;
import com.background.manager.model.dto.request.home.PageQueryTypicalClassRequest;
import com.background.manager.model.dto.response.home.TypicalClassDTO;
import com.background.manager.model.dto.response.home.TypicalClassDigestDTO;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundTypicalClassMapper;
import com.background.manager.model.BackgroundTypicalClass;
import com.background.manager.service.BackgroundTypicalClassService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class BackgroundTypicalClassServiceImpl extends ServiceImpl<BackgroundTypicalClassMapper, BackgroundTypicalClass> implements BackgroundTypicalClassService {

    @Resource
    private HomeConvertor homeConvertor;

    @Override
    public String addTypicalClass(AddTypicalClassRequest request) {
        String title = request.getTitle();
        BackgroundTypicalClass typicalClass = this.getOne(new LambdaQueryWrapper<BackgroundTypicalClass>().eq(BackgroundTypicalClass::getTitle, title));
        if (ObjectUtil.isNotNull(typicalClass)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.TYPICAL_CLASS_ENTITY_TITLE_REPEAT_ERROR);
        }
        BackgroundTypicalClass backgroundTypicalClass=homeConvertor.toBackgroundTypicalClass(request);
        this.save(backgroundTypicalClass);
        return backgroundTypicalClass.getTitle();
    }

    @Override
    public IPage<TypicalClassDigestDTO> pageQuery(PageQueryTypicalClassRequest request) {
        Page<BackgroundTypicalClass> page = this.lambdaQuery().page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        IPage<TypicalClassDigestDTO> pages=new Page<>(page.getCurrent(),page.getSize());
        pages.setPages(page.getPages());
        pages.setTotal(page.getTotal());
        pages.setRecords(homeConvertor.toBackgroundTypicalClassDigestDTOS(page.getRecords()));
        return pages;
    }

    @Override
    public TypicalClassDTO getTypicalClass(Long id) {
        BackgroundTypicalClass backgroundTypicalClass = this.getOne(new LambdaQueryWrapper<BackgroundTypicalClass>()
                .eq(BackgroundTypicalClass::getId, id));
        if (ObjectUtil.isNull(backgroundTypicalClass)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.TYPICAL_CLASS_ENTITY_NOT_EXIST_ERROR);
        }
        return homeConvertor.toBackgroundTypicalClassDTO(backgroundTypicalClass);
    }

    @Override
    public void modify(ModifyTypicalClassRequest request) {
        BackgroundTypicalClass backgroundTypicalClass = this.getOne(new LambdaQueryWrapper<BackgroundTypicalClass>()
                .eq(BackgroundTypicalClass::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundTypicalClass)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.TYPICAL_CLASS_ENTITY_NOT_EXIST_ERROR);
        }
        BackgroundTypicalClass repeatNameBackgroundTypicalClass = this.getOne(new LambdaQueryWrapper<BackgroundTypicalClass>()
                .eq(BackgroundTypicalClass::getTitle,request.getTitle())
                .ne(BackgroundTypicalClass::getId, request.getId()));
        if (ObjectUtil.isNotNull(repeatNameBackgroundTypicalClass)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.TYPICAL_CLASS_ENTITY_TITLE_REPEAT_ERROR);
        }
        BackgroundTypicalClass update = homeConvertor.toBackgroundTypicalClass(request);
        this.updateById(update);
    }

    @Override
    public void delete(Long id) {
        BackgroundTypicalClass backgroundTypicalClass = this.getOne(new LambdaQueryWrapper<BackgroundTypicalClass>()
                .eq(BackgroundTypicalClass::getId, id));
        if (ObjectUtil.isNull(backgroundTypicalClass)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.TYPICAL_CLASS_ENTITY_NOT_EXIST_ERROR);
        }
        this.removeById(backgroundTypicalClass);
    }

}
