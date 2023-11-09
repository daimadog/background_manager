package com.background.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.convert.BackgroundLogConvertor;
import com.background.manager.dto.request.log.DeleteOperationLogRequest;
import com.background.manager.dto.request.log.PageQueryOperationLogRequest;
import com.background.manager.dto.response.log.OperationLogDigestDTO;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.BackgroundOperationLog;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundOperationLogMapper;
import com.background.manager.service.BackgroundOperationLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 操作日志接口实现类
 * @Author: 杜黎明
 * @Date: 2022/10/17 09:47:32
 * @Version: 1.0.0
 */
@Service
public class BackgroundOperationLogServiceImpl extends ServiceImpl<BackgroundOperationLogMapper, BackgroundOperationLog> implements BackgroundOperationLogService {

    @Resource
    private BackgroundLogConvertor backgroundLogConvertor;

    @Override
    public IPage<OperationLogDigestDTO> pageQuery(PageQueryOperationLogRequest request) {
        IPage<BackgroundOperationLog> page = this.lambdaQuery()
                .ge(StringUtils.isNotEmpty(request.getStartTime()), BackgroundOperationLog::getCreateTime, request.getStartTime())
                .lt(StringUtils.isNotEmpty(request.getEndTime()), BackgroundOperationLog::getCreateTime, request.getEndTime())
                /**关键词匹配操作人、操作Ip、操作地址、请求类型、返回结果编码、返回状态*/
                .and(StringUtils.isNotBlank(request.getKeyWord()),log->log.like(StringUtils.isNotBlank(request.getKeyWord()),BackgroundOperationLog::getRequestName,request.getKeyWord())
                .or().like(StringUtils.isNotBlank(request.getKeyWord()), BackgroundOperationLog::getCreator, request.getKeyWord())
                .or().like(StringUtils.isNotBlank(request.getKeyWord()), BackgroundOperationLog::getCreatorIp, request.getKeyWord())
                .or().like(StringUtils.isNotBlank(request.getKeyWord()), BackgroundOperationLog::getCreatorAddress, request.getKeyWord())
                .or().eq(StringUtils.isNotBlank(request.getKeyWord()), BackgroundOperationLog::getRequestType, request.getKeyWord())
                .or().eq(StringUtils.isNotBlank(request.getKeyWord()), BackgroundOperationLog::getResCode, request.getKeyWord()))
                .eq(ObjectUtil.isNotNull(request.getRequestStatus()),BackgroundOperationLog::getRequestStatus,request.getRequestStatus())
                .orderByDesc(BackgroundOperationLog::getCreateTime)
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        Page<OperationLogDigestDTO> pages=new Page(page.getCurrent(),page.getSize());
        pages.setPages(page.getPages());
        pages.setTotal(page.getTotal());
        pages.setRecords(backgroundLogConvertor.toOperationLogDTOS(page.getRecords()));
        return pages;
    }

    @Override
    public boolean delete(DeleteOperationLogRequest request) {
        if (CollectionUtil.isEmpty(request.getIdList())){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return this.removeBatchByIds(request.getIdList());
    }

    @Override
    public boolean clean() {
        List<BackgroundOperationLog> backgroundLoginLogs = this.list(new LambdaQueryWrapper<BackgroundOperationLog>());
        if (CollectionUtil.isEmpty(backgroundLoginLogs)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ENTITY_NOT_EXIT_ERROR);
        }
        return this.removeBatchByIds(backgroundLoginLogs);
    }


}
