package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.BackgroundSeoRuleConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundSeoRuleMapper;
import com.background.manager.model.BackgroundSeoRule;
import com.background.manager.model.dto.request.seo.AddSeoRuleRequest;
import com.background.manager.model.dto.request.seo.ModifySeoRuleRequest;
import com.background.manager.model.dto.request.seo.PageQuerySeoRuleRequest;
import com.background.manager.model.dto.response.seo.BackgroundSeoRuleDTO;
import com.background.manager.service.BackgroundSeoRuleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BackgroundSeoRuleServiceImpl extends ServiceImpl<BackgroundSeoRuleMapper, BackgroundSeoRule> implements BackgroundSeoRuleService {

    @Resource
    private BackgroundSeoRuleConvertor backgroundSeoRuleConvertor;


    @Override
    public void add(AddSeoRuleRequest request) {
        BackgroundSeoRule backgroundSeoRule=new BackgroundSeoRule();
        backgroundSeoRule.setSeoRule(request.getSeoRule());
        if (StpUtil.isLogin()){
            backgroundSeoRule.setCreator(StpUtil.getLoginIdAsString());
            backgroundSeoRule.setModifier(StpUtil.getLoginIdAsString());
        }
        backgroundSeoRule.setCreateTime(LocalDateTime.now());
        backgroundSeoRule.setUpdateTime(LocalDateTime.now());
        this.save(backgroundSeoRule);
    }

    @Override
    public void delete(Long id) {
        BackgroundSeoRule backgroundSeoRule = this.getOne(
                new LambdaQueryWrapper<BackgroundSeoRule>()
                        .eq(BackgroundSeoRule::getId, id)
        );
        if (ObjectUtil.isNull(backgroundSeoRule)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.removeById(backgroundSeoRule);
    }

    @Override
    public IPage<BackgroundSeoRuleDTO> pageQuery(PageQuerySeoRuleRequest request) {
        Page<BackgroundSeoRule> page = this.lambdaQuery()
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return null;
        }
        Page<BackgroundSeoRuleDTO> pages=new Page<>();
        pages.setPages(page.getPages())
                .setSize(page.getSize())
                .setTotal(page.getTotal())
                .setRecords(backgroundSeoRuleConvertor.toBackgroundSeoRuleDTOList(page.getRecords()));
        return pages;
    }

    @Override
    public List<BackgroundSeoRuleDTO> listQuery() {
        List<BackgroundSeoRule> backgroundSeoRuleList = this.list(
                new LambdaQueryWrapper<BackgroundSeoRule>()
        );
        return backgroundSeoRuleConvertor.toBackgroundSeoRuleDTOList(backgroundSeoRuleList);
    }

    @Override
    public void modify(ModifySeoRuleRequest request) {
        BackgroundSeoRule backgroundSeoRule = this.getOne(
                new LambdaQueryWrapper<BackgroundSeoRule>()
                        .eq(BackgroundSeoRule::getId, request.getId())
        );
        if (ObjectUtil.isNull(backgroundSeoRule)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        BackgroundSeoRule seoRule = backgroundSeoRuleConvertor.toBackgroundSeoRule(request);
        seoRule.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            seoRule.setModifier(StpUtil.getLoginIdAsString());
        }
        this.updateById(seoRule);
    }


}
