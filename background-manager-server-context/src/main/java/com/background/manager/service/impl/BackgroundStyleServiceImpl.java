package com.background.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.BackgroundStyleConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundStyleMapper;
import com.background.manager.model.BackgroundStyle;
import com.background.manager.model.dto.request.style.ListQueryBackgroundStyleRequest;
import com.background.manager.model.dto.request.style.ModifyBackgroundStyleRequest;
import com.background.manager.model.dto.response.style.BackgroundStyleDTO;
import com.background.manager.service.BackgroundStyleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BackgroundStyleServiceImpl extends ServiceImpl<BackgroundStyleMapper, BackgroundStyle>  implements BackgroundStyleService {

    private final BackgroundStyleConvertor backgroundStyleConvertor;

    @Override
    public List<BackgroundStyleDTO> listQuery(ListQueryBackgroundStyleRequest request) {
        List<BackgroundStyle> backgroundStyleList = this.list(
                new LambdaQueryWrapper<BackgroundStyle>()
                        .like(StringUtils.isNotBlank(request.getName()), BackgroundStyle::getName, request.getName())
        );
            return backgroundStyleConvertor.toBackgroundStyleDTOS(backgroundStyleList);
    }

    @Override
    public void modify(ModifyBackgroundStyleRequest request) {
        BackgroundStyle backgroundStyle = this.getOne(
                new LambdaQueryWrapper<BackgroundStyle>()
                        .eq(BackgroundStyle::getId, request.getId())
        );
        if (ObjectUtil.isNull(backgroundStyle)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        backgroundStyle.setColor(request.getColor());
        this.updateById(backgroundStyle);
    }

}
