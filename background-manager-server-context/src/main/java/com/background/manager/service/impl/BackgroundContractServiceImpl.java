package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.business.IBusinessClient;
import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.convert.BackgroundContractConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundContractMapper;
import com.background.manager.model.BackgroundContract;
import com.background.manager.model.dto.request.contract.*;
import com.background.manager.model.dto.response.contract.BackgroundContractDTO;
import com.background.manager.model.dto.response.contract.BackgroundContractDigestDTO;
import com.background.manager.model.dto.response.receipt.BackgroundContractUserDigestDTO;
import com.background.manager.service.BackgroundContractService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description：合同管理接口实现类
 * @Author: 杜黎明
 * @Date: 2023/01/05 10:52:23
 * @Version: 1.0.0
 */
@Service
@AllArgsConstructor
public class BackgroundContractServiceImpl extends ServiceImpl<BackgroundContractMapper,BackgroundContract> implements BackgroundContractService {

    private final BackgroundContractConvertor backgroundContractConvertor;

    @Resource
    private IBusinessClient iBusinessClient;


    @Override
    public String create(CreateContractRequest request) {
        //校验合同名称是否重复
        BackgroundContract repeatNameContract = this.getOne(new LambdaQueryWrapper<BackgroundContract>()
                .eq(BackgroundContract::getName, request.getName()));
        if (ObjectUtil.isNotNull(repeatNameContract)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_NAME_REPEAT_ERROR);
        }
        //校验合同编号是否重复
        BackgroundContract repeatCodeContract = this.getOne(new LambdaQueryWrapper<BackgroundContract>()
                .eq(BackgroundContract::getCode, request.getCode()));
        if (ObjectUtil.isNotNull(repeatCodeContract)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_CODE_REPEAT_ERROR);
        }
        BackgroundContract backgroundContract=backgroundContractConvertor.toBackgroundContract(request);
        backgroundContract.setCreateTime(LocalDateTime.now());
        backgroundContract.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            String loginId = StpUtil.getLoginIdAsString();
            backgroundContract.setCreator(loginId);
            backgroundContract.setModifier(loginId);
        }
        /**合同附件上传功能调用文件上传接口*/
        this.save(backgroundContract);
        return backgroundContract.getName();
    }

    @Override
    public IPage<BackgroundContractDigestDTO> pageQuery(PageQueryContractRequest request) {
        Page<BackgroundContract> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(request.getName()), BackgroundContract::getName, request.getName())
                .eq(StringUtils.isNotBlank(request.getCode()), BackgroundContract::getCode, request.getCode())
                .eq(ObjectUtil.isNotNull(request.getSignTime()), BackgroundContract::getSignTime, request.getSignTime())
                .ge(ObjectUtil.isNotNull(request.getStartTime()), BackgroundContract::getStartTime, request.getStartTime())
                .lt(ObjectUtil.isNotNull(request.getEndTime()), BackgroundContract::getEndTime, request.getEndTime())
                .eq(ObjectUtil.isNotNull(request.getAmount()), BackgroundContract::getAmount, request.getAmount())
                .eq(ObjectUtil.isNotNull(request.getStatus()), BackgroundContract::getStatus, request.getStatus())
                .eq(StringUtils.isNotBlank(request.getAccountId()),BackgroundContract::getAccountId,request.getAccountId())
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        List<BackgroundContractDigestDTO>  contractDigestDTOS=new ArrayList<>();
        for (BackgroundContract amp:page.getRecords()){
            BackgroundContractDigestDTO dto=backgroundContractConvertor.toBackgroundContractDigestDTO(amp);
            //封装合同所属账户信息
            ITsysUserDTO user = iBusinessClient.getTsysUserByAccountId(amp.getAccountId());
            if (ObjectUtil.isNotNull(user)){
                dto.setCompanyName(user.getCompanyName());
            }
            contractDigestDTOS.add(dto);
        }
        IPage<BackgroundContractDigestDTO> pages=new Page<>();
           pages.setPages(page.getPages())
                .setTotal(page.getTotal())
                .setSize(page.getSize())
                .setCurrent(page.getCurrent())
                .setRecords(contractDigestDTOS);
        return pages;
    }

    @Override
    public void edit(ModifyContractRequest request) {
        BackgroundContract backgroundContract = this.getOne(new LambdaQueryWrapper<BackgroundContract>()
                .eq(BackgroundContract::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundContract)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_ENTITY_NOT_EXIST_ERROR);
        }
        //校验修改的合同名称是否重复
        BackgroundContract repeatNameContract = this.getOne(new LambdaQueryWrapper<BackgroundContract>()
                .eq(BackgroundContract::getName, request.getName())
                .ne(BackgroundContract::getId,request.getId()));
        if (ObjectUtil.isNotNull(repeatNameContract)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_NAME_REPEAT_ERROR);
        }
        //校验修改后的合同编号是否重复
        BackgroundContract repeatCodeContract = this.getOne(new LambdaQueryWrapper<BackgroundContract>()
                .eq(BackgroundContract::getCode, request.getCode())
                .ne(BackgroundContract::getId,request.getId()));
        if (ObjectUtil.isNotNull(repeatCodeContract)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_CODE_REPEAT_ERROR);
        }
        BackgroundContract modifyBackgroundContract=backgroundContractConvertor.toBackgroundContract(request);
        modifyBackgroundContract.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            modifyBackgroundContract.setModifier(StpUtil.getLoginIdAsString());
        }
        this.updateById(modifyBackgroundContract);
    }

    @Override
    public void audit(AuditContactRequest request) {
        BackgroundContract backgroundContract = this.getOne(new LambdaQueryWrapper<BackgroundContract>()
                .eq(BackgroundContract::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundContract)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_ENTITY_NOT_EXIST_ERROR);
        }
        backgroundContract.setStatus(request.getStatus());
        backgroundContract.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            backgroundContract.setModifier(StpUtil.getLoginIdAsString());
        }
        this.updateById(backgroundContract);
    }

    @Override
    public void delete(Long id) {
        BackgroundContract backgroundContract = this.getOne(new LambdaQueryWrapper<BackgroundContract>()
                .eq(BackgroundContract::getId, id));
        if (ObjectUtil.isNull(backgroundContract)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_ENTITY_NOT_EXIST_ERROR);
        }
        this.removeById(backgroundContract);
    }

    @Override
    public BackgroundContractDTO getContract(Long id) {
        BackgroundContract backgroundContract = this.getOne(new LambdaQueryWrapper<BackgroundContract>()
                .eq(BackgroundContract::getId, id));
        if (ObjectUtil.isNull(backgroundContract)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.CONTRACT_ENTITY_NOT_EXIST_ERROR);
        }
        BackgroundContractDTO backgroundContractDTO=backgroundContractConvertor.toBackgroundContactDTO(backgroundContract);
        //封装合同所属账户信息
        ITsysUserDTO user = iBusinessClient.getTsysUserByAccountId(backgroundContract.getAccountId());
        if (ObjectUtil.isNotNull(user)){
            backgroundContractDTO.setCompanyName(user.getCompanyName());
        }
        return  backgroundContractDTO;
    }

    @Override
    public IPage<BackgroundContractUserDigestDTO> pageQueryUserContract(pageQueryUserContractRequest request) {
        String loginId = StpUtil.getLoginIdAsString();
        Page<BackgroundContract> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(request.getName()), BackgroundContract::getName, request.getName())
                .eq(StringUtils.isNotBlank(request.getCode()), BackgroundContract::getCode, request.getCode())
                .eq(ObjectUtil.isNotNull(request.getStatus()), BackgroundContract::getStatus, request.getStatus())
                .eq(BackgroundContract::getAccountId,loginId)
                .page(new Page<>(request.getPage(), request.getSize()));

        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        IPage<BackgroundContractUserDigestDTO> pages=new Page<>();
        pages.setPages(page.getPages())
                .setTotal(page.getTotal())
                .setSize(page.getSize())
                .setCurrent(page.getCurrent())
                .setRecords(backgroundContractConvertor.toBackgroundContractUserDigestDTOS(page.getRecords()));
        return pages;
    }


}
