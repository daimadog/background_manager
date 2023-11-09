package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.business.IBusinessClient;
import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.constant.StatusConstant;
import com.background.manager.convert.BackgroundReceiptConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundReceiptMapper;
import com.background.manager.model.BackgroundContract;
import com.background.manager.model.BackgroundReceipt;
import com.background.manager.model.dto.request.receipt.*;
import com.background.manager.model.dto.response.contract.BackgroundContractDTO;
import com.background.manager.model.dto.response.receipt.ReceiptDTO;
import com.background.manager.model.dto.response.receipt.ReceiptDigestDTO;
import com.background.manager.service.BackgroundContractService;
import com.background.manager.service.BackgroundReceiptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Description: 发票管理接口实现类
 * @Author: 杜黎明
 * @Date: 2023/01/06 17:12:43
 * @Version: 1.0.0
 */
@Service
public class BackgroundReceiptServiceImpl extends ServiceImpl<BackgroundReceiptMapper, BackgroundReceipt> implements BackgroundReceiptService {

    @Resource
    private BackgroundReceiptConvertor backgroundReceiptConvertor;

    @Resource
    private IBusinessClient iBusinessClient;

    @Resource
    private BackgroundContractService backgroundContractService;


    /**
     * Function函数封装发票信息
     */
    private final Function<List<? extends BackgroundReceipt>,List<ReceiptDigestDTO>> dealWithReceipt=(receipt) ->{
        List<ReceiptDigestDTO> receiptDigestDTOS=new ArrayList<>();
        for (BackgroundReceipt amp:receipt){
            ReceiptDigestDTO receiptDigestDTO = backgroundReceiptConvertor.toReceiptDigestDTO(amp);
            //封装发票-用户信息
            ITsysUserDTO user = iBusinessClient.getTsysUserByAccountId(amp.getCreator());
            if (ObjectUtil.isNotNull(user)){
                receiptDigestDTO.setUsername(user.getUsername());
            }
            //封装发票-合同信息
            BackgroundContractDTO contract = backgroundContractService.getContract(amp.getContractId());
            if (ObjectUtils.allNotNull()){
                receiptDigestDTO.setContractName(contract.getName());
            }
            receiptDigestDTOS.add(receiptDigestDTO);
        }
        return receiptDigestDTOS;
    };


    @Override
    public void add(AddReceiptRequest request) {
        BackgroundReceipt backgroundReceipt=backgroundReceiptConvertor.toBackgroundReceipt(request);
        backgroundReceipt.setReceiptTime(LocalDateTime.now());
        backgroundReceipt.setCreateTime(LocalDateTime.now());
        backgroundReceipt.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            backgroundReceipt.setCreator(StpUtil.getLoginIdAsString());
            backgroundReceipt.setModifier(StpUtil.getLoginIdAsString());
        }
        backgroundReceipt.setStatus(StatusConstant.RECEIPT_NOT_INVOICED);
        this.save(backgroundReceipt);
    }

    @Override
    @Transactional
    public IPage<ReceiptDigestDTO> pageQuery(PageQueryReceiptRequest request) {
        IPage<BackgroundReceipt> page = this.lambdaQuery()
                .eq(ObjectUtils.isNotEmpty(request.getReceiptTime()), BackgroundReceipt::getReceiptTime, request.getReceiptTime())
                .eq(StringUtils.isNotBlank(request.getAccount()),BackgroundReceipt::getCreator,request.getAccount())
                .eq(ObjectUtils.isNotEmpty(request.getType()),BackgroundReceipt::getType,request.getType())
                .like(StringUtils.isNotBlank(request.getTitle()),BackgroundReceipt::getTitle,request.getTitle())
                .like(StringUtils.isNotBlank(request.getRemarks()),BackgroundReceipt::getRemarks,request.getRemarks())
                .eq(ObjectUtils.isNotEmpty(request.getStatus()),BackgroundReceipt::getStatus,request.getStatus())
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        List<ReceiptDigestDTO> receiptDigestDTOS=new ArrayList<>();
        for (BackgroundReceipt amp:page.getRecords()){
            ReceiptDigestDTO receiptDigestDTO = backgroundReceiptConvertor.toReceiptDigestDTO(amp);
            //封装发票-用户信息
            ITsysUserDTO user = iBusinessClient.getTsysUserByAccountId(amp.getCreator());
            if (ObjectUtil.isNotNull(user)){
                receiptDigestDTO.setUsername(user.getUsername());
            }
            //封装发票-合同信息
            BackgroundContractDTO contract = backgroundContractService.getContract(amp.getContractId());
            if (ObjectUtils.allNotNull()){
                receiptDigestDTO.setContractName(contract.getName());
            }
            receiptDigestDTOS.add(receiptDigestDTO);
        }
        Page<ReceiptDigestDTO> pages=new Page<>();
        pages.setPages(page.getPages())
                .setTotal(page.getTotal())
                .setSize(page.getSize())
                .setCurrent(page.getCurrent())
                .setRecords(receiptDigestDTOS);
        return pages;
    }

    @Override
    public void modify(ModifyReceiptRequest request) {
        BackgroundReceipt backgroundReceipt = this.getOne(new LambdaQueryWrapper<BackgroundReceipt>()
                .eq(BackgroundReceipt::getId, request.getId()));
        if (ObjectUtils.isEmpty(backgroundReceipt)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        BackgroundReceipt modifyBackgroundReceipt = backgroundReceiptConvertor.toBackgroundReceipt(request);
        modifyBackgroundReceipt.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            modifyBackgroundReceipt.setModifier(StpUtil.getLoginIdAsString());
        }
        this.updateById(modifyBackgroundReceipt);
    }

    @Override
    public void delete(Long id) {
        BackgroundReceipt backgroundReceipt = this.getOne(new LambdaQueryWrapper<BackgroundReceipt>()
                .eq(BackgroundReceipt::getId, id));
        if (ObjectUtils.isEmpty(backgroundReceipt)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        this.removeById(backgroundReceipt);
    }

    @Override
    public ReceiptDTO getReceipt(Long id) {
        BackgroundReceipt backgroundReceipt = this.getOne(new LambdaQueryWrapper<BackgroundReceipt>()
                .eq(BackgroundReceipt::getId, id));
        if (ObjectUtils.isEmpty(backgroundReceipt)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        ReceiptDTO receiptDTO = backgroundReceiptConvertor.toReceiptDTO(backgroundReceipt);
        BackgroundContractDTO contract = backgroundContractService.getContract(backgroundReceipt.getContractId());
        if (ObjectUtil.isNotNull(contract)){
            receiptDTO.setContractCode(contract.getCode());
            receiptDTO.setContractName(contract.getName());
        }
        return  receiptDTO;
    }

    @Override
    @Transactional
    public void audit(AuditReceiptRequest request) {
        BackgroundReceipt backgroundReceipt = this.getOne(new LambdaQueryWrapper<BackgroundReceipt>()
                .eq(BackgroundReceipt::getId, request.getId()));
        if (ObjectUtils.isEmpty(backgroundReceipt)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        backgroundReceipt.setApplyStatus(request.getApplyStatus());
        //修改对应发票状态
        backgroundReceipt.setStatus(request.getApplyStatus());
        backgroundReceipt.setRemarks(request.getRemarks());
        backgroundReceipt.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            backgroundReceipt.setModifier(StpUtil.getLoginIdAsString());
        }
        this.updateById(backgroundReceipt);
        if (StatusConstant.RECEIPT_REJECT.equals(request.getApplyStatus().intValue())){
            backgroundContractService.update(new LambdaUpdateWrapper<BackgroundContract>()
                    .eq(BackgroundContract::getId,backgroundReceipt.getContractId())
                    .set(BackgroundContract::getReceiptStatus,0));
        }
    }

}
