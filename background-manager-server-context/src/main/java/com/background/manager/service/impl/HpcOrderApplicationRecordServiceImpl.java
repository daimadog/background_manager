package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.business.IBusinessClient;
import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.convert.HpcOrderApplicationRecordConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.HpcOrderApplicationRecordMapper;
import com.background.manager.model.HpcOrderApplicationRecord;
import com.background.manager.model.dto.request.order.AuditOrderRequest;
import com.background.manager.model.dto.request.order.pageQueryHpcOrderRequest;
import com.background.manager.model.dto.response.order.HpcOrderApplicationRecordDTO;
import com.background.manager.service.HpcOrderApplicationRecordService;
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
public class HpcOrderApplicationRecordServiceImpl extends ServiceImpl<HpcOrderApplicationRecordMapper, HpcOrderApplicationRecord> implements HpcOrderApplicationRecordService {

    @Resource
    private HpcOrderApplicationRecordConvertor hpcOrderApplicationRecordConvertor;

    @Resource
    private IBusinessClient iBusinessClient;


    @Override
    public IPage<HpcOrderApplicationRecordDTO> pageQueryHpcApplication(pageQueryHpcOrderRequest request) {
        Page<HpcOrderApplicationRecord> page = this.lambdaQuery()
                .eq(StringUtil.isNotBlank(request.getOrderId()), HpcOrderApplicationRecord::getOrderId, request.getOrderId())
                .eq(ObjectUtil.isNotNull(request.getApplyStatus()), HpcOrderApplicationRecord::getApplyStatus, request.getApplyStatus())
                .page(new Page<>(request.getPage(), request.getSize()));
        IPage<HpcOrderApplicationRecordDTO> pages=new Page<>();
        if (CollectionUtils.isEmpty(page.getRecords())){
            return pages;
        }
        List<HpcOrderApplicationRecordDTO> records=new ArrayList<>();
        for (HpcOrderApplicationRecord hpcOrderApplicationRecord:page.getRecords()){
            //封装HpcOrderApplicationRecordDTO
            HpcOrderApplicationRecordDTO hpcOrderApplicationRecordDTO = hpcOrderApplicationRecordConvertor.toHpcOrderApplicationRecordDTO(hpcOrderApplicationRecord);
            ITsysUserDTO user = iBusinessClient.getTsysUserByAccountId(hpcOrderApplicationRecord.getAccountId());
            /**用户删除注销时，不显示HPC订单充值记录*/
            if (ObjectUtil.isNotNull(user)){
                hpcOrderApplicationRecordDTO.setUsername(user.getUsername());
                records.add(hpcOrderApplicationRecordDTO);
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
    public void auditHpcOrderApply(AuditOrderRequest request) {
        HpcOrderApplicationRecord hpcOrderApplicationRecord = this.getOne(
                new LambdaQueryWrapper<HpcOrderApplicationRecord>()
                        .eq(HpcOrderApplicationRecord::getId, request.getId()));
        if (ObjectUtil.isNull(hpcOrderApplicationRecord)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        hpcOrderApplicationRecord.setApplyStatus(request.getApplyStatus());
        if (StringUtils.isNotBlank(request.getRemark())){
            hpcOrderApplicationRecord.setRemark(request.getRemark());
        }
        if (StpUtil.isLogin()){
            hpcOrderApplicationRecord.setModifier(StpUtil.getLoginIdAsString());
        }
        hpcOrderApplicationRecord.setUpdateTime(LocalDateTime.now());
        this.updateById(hpcOrderApplicationRecord);
    }


}
