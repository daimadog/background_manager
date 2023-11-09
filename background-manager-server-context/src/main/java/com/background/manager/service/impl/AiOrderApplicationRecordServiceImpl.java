package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.business.IBusinessClient;
import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.convert.AiOrderApplicationRecordConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.AiOrderApplicationRecordMapper;
import com.background.manager.model.AiOrderApplicationRecord;
import com.background.manager.model.dto.request.order.AuditOrderRequest;
import com.background.manager.model.dto.request.order.pageQueryAiOrderRequest;
import com.background.manager.model.dto.response.order.AiOrderApplicationRecordDTO;
import com.background.manager.service.AiOrderApplicationRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AiOrderApplicationRecordServiceImpl extends ServiceImpl<AiOrderApplicationRecordMapper, AiOrderApplicationRecord> implements AiOrderApplicationRecordService {

        @Resource
        private AiOrderApplicationRecordConvertor aiOrderApplicationRecordConvertor;

        @Resource
        private IBusinessClient iBusinessClient;

    @Override
    public IPage<AiOrderApplicationRecordDTO> pageQueryAiApplication(pageQueryAiOrderRequest request) {
        Page<AiOrderApplicationRecord> page = this.lambdaQuery()
                .eq(StringUtil.isNotBlank(request.getOrderSn()), AiOrderApplicationRecord::getOrderSn,request.getOrderSn())
                .eq(ObjectUtil.isNotNull(request.getApplyStatus()), AiOrderApplicationRecord::getApplyStatus, request.getApplyStatus())
                .eq(StringUtil.isNotBlank(request.getProductName()),AiOrderApplicationRecord::getProductName,request.getProductName())
                .page(new Page<>(request.getPage(), request.getSize()));
        IPage<AiOrderApplicationRecordDTO> pages=new Page<>();
        if (CollectionUtils.isEmpty(page.getRecords())){
            return pages;
        }
        List<AiOrderApplicationRecordDTO> records=new ArrayList<>();
        for (AiOrderApplicationRecord aiOrderApplicationRecord:page.getRecords()){
            //封装HpcOrderApplicationRecordDTO
            AiOrderApplicationRecordDTO aiOrderApplicationRecordDTO = aiOrderApplicationRecordConvertor.toAiOrderApplicationRecordDTO(aiOrderApplicationRecord);
            ITsysUserDTO user = iBusinessClient.getTsysUserByAccountId(aiOrderApplicationRecord.getAccountId());
            /**用户删除注销时，不显示AI订单充值记录*/
            if (ObjectUtil.isNotNull(user)){
                aiOrderApplicationRecordDTO.setUsername(user.getUsername());
                records.add(aiOrderApplicationRecordDTO);
            }
        }
        pages.setPages(page.getPages())
                .setSize(page.getSize())
                .setTotal(page.getTotal())
                .setCurrent(page.getCurrent())
                .setRecords(records);
        return pages;
    }

    @Override
    public void auditAiOrderApply(AuditOrderRequest request) {
        AiOrderApplicationRecord aiOrderApplicationRecord = this.getOne(
                new LambdaQueryWrapper<AiOrderApplicationRecord>()
                        .eq(AiOrderApplicationRecord::getId, request.getId()));
        if (ObjectUtil.isNull(aiOrderApplicationRecord)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        aiOrderApplicationRecord.setApplyStatus(request.getApplyStatus());
        if (StringUtils.isNotBlank(request.getRemark())){
            aiOrderApplicationRecord.setRemark(request.getRemark());
        }
        if (StpUtil.isLogin()){
            aiOrderApplicationRecord.setModifier(StpUtil.getLoginIdAsString());
        }
        aiOrderApplicationRecord.setUpdateTime(LocalDateTime.now());
        this.updateById(aiOrderApplicationRecord);
    }

}
