package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.business.IBusinessClient;
import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.ContractApplicationRecordMapper;
import com.background.manager.model.BackgroundContract;
import com.background.manager.model.ContractApplicationRecord;
import com.background.manager.model.dto.request.contract.ApplyRechargeContractRequest;
import com.background.manager.model.dto.request.contract.PageQueryReChargeContractRequest;
import com.background.manager.model.dto.response.contract.ReChargeContractDTO;
import com.background.manager.service.BackgroundContractService;
import com.background.manager.service.ContractApplicationRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractApplicationRecordServiceImpl extends ServiceImpl<ContractApplicationRecordMapper, ContractApplicationRecord> implements ContractApplicationRecordService {

    @Resource
    private com.background.manager.convert.ContractApplicationRecordConvertor ContractApplicationRecordConvertor;

    @Resource
    private IBusinessClient iBusinessClient;

    @Resource
    private BackgroundContractService backgroundContractService;

    @Override
    public IPage<ReChargeContractDTO> PageQueryReChargeContract(PageQueryReChargeContractRequest request) {
        Page<ContractApplicationRecord> page = this.lambdaQuery()
                .eq(ObjectUtil.isNotNull(request.getContractId()), ContractApplicationRecord::getContractId,request.getContractId())
                .eq(ObjectUtil.isNotNull(request.getRechargeStatus()), ContractApplicationRecord::getRechargeStatus, request.getRechargeStatus())
                .page(new Page<>(request.getPage(), request.getSize()));
        IPage<ReChargeContractDTO> pages=new Page<>();
        //封装ReChargeContractDTO
        if (CollectionUtils.isEmpty(page.getRecords())){
            return pages;
        }
        List<ReChargeContractDTO> pagesRecord=new ArrayList<>();
        for (ContractApplicationRecord amp:page.getRecords()){
            ReChargeContractDTO reChargeContractDTO = ContractApplicationRecordConvertor.toReChargeContractDTO(amp);
            //获取账户信息
            ITsysUserDTO tsysUserDTO = iBusinessClient.getTsysUserByAccountId(amp.getAccountId());
            /**用户删除注销时，不显示合同充值记录*/
            if (ObjectUtil.isNotNull(tsysUserDTO)){
                reChargeContractDTO.setUsername(tsysUserDTO.getUsername());
                //获取合同信息
                BackgroundContract backgroundContract = backgroundContractService.getOne(new LambdaQueryWrapper<BackgroundContract>()
                        .eq(BackgroundContract::getId, amp.getContractId()));
                reChargeContractDTO.setContractName(backgroundContract.getName());
                reChargeContractDTO.setContractCode(backgroundContract.getCode());
                reChargeContractDTO.setContractAmount(backgroundContract.getAmount());
                pagesRecord.add(reChargeContractDTO);
            }
        }
        pages.setPages(page.getPages())
                .setSize(page.getSize())
                .setTotal(page.getTotal())
                .setCurrent(page.getCurrent())
                .setRecords(pagesRecord);
        return pages;
    }

    @Override
    public void apply(ApplyRechargeContractRequest request) {
        ContractApplicationRecord contractApplicationRecord = this.getOne(
                new LambdaQueryWrapper<ContractApplicationRecord>()
                        .eq(ContractApplicationRecord::getId, request.getId()));
        if (ObjectUtil.isNull(contractApplicationRecord)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        contractApplicationRecord.setRechargeStatus(request.getRechargeStatus());
        if (StringUtils.isNotBlank(request.getRemark())){
            contractApplicationRecord.setRemark(request.getRemark());
        }
        if (StpUtil.isLogin()){
            contractApplicationRecord.setModifier(StpUtil.getLoginIdAsString());
        }
        contractApplicationRecord.setUpdateTime(LocalDateTime.now());
        this.updateById(contractApplicationRecord);
    }

}
