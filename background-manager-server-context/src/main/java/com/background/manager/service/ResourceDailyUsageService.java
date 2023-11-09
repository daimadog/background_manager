package com.background.manager.service;

import com.background.manager.model.dto.request.resource.AddResourceDailyUsage;
import com.background.manager.model.dto.request.resource.FindResourceDailyUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyResourceDailyUsageRequest;
import com.background.manager.model.dto.response.resource.ResourceDailyUsageDTO;
import com.background.manager.model.resource.ResourceDailyUsage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Date;
import java.util.List;

public interface ResourceDailyUsageService extends IService<ResourceDailyUsage> {

    /**
     * Description: 新增资源单日使用情况
     * @param request 请求
     * @author 杜黎明
     * @date 2022/12/08 23:26:29
     */
    void add(AddResourceDailyUsage request);

    /**
     * Description:查找指定时间段的单日资源使用情况
     * @param request
     * @return {@link List<ResourceDailyUsageDTO> }
     * @author 杜黎明
     * @date 2022/12/08 23:26:47
     */
    ResourceDailyUsageDTO findResource(FindResourceDailyUsageRequest request);

    /**
     * Description: 编辑资源单日使用情况
     * @param request 请求
     * @author 杜黎明
     * @date 2022/12/20 11:24:30
     */
    void modifyResourceDailyUsage(ModifyResourceDailyUsageRequest request);

    /**
     * Description: 删除指定编号的资源单日使用情况
     * @param date 日期
     * @author 杜黎明
     * @date 2022/12/20 14:29:18
     */
    void deleteResourceDailyUsage(Date date);
}
