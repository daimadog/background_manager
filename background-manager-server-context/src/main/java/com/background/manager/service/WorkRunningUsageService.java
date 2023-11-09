package com.background.manager.service;

import com.background.manager.model.dto.request.resource.AddWorkRunningUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyWorkRunningUsageRequest;
import com.background.manager.model.dto.response.resource.WorkRunningUsageDTO;
import com.background.manager.model.resource.WorkRunningUsage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface WorkRunningUsageService extends IService<WorkRunningUsage> {
    /**
     * Description: 新增运行节点
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/12/08 15:21:43
     */
    String add(AddWorkRunningUsageRequest request);

    /**
     * Description: 查找指定运行节点
     * @param id id
     * @return {@link WorkRunningUsageDTO }
     * @author 杜黎明
     * @date 2022/12/08 15:33:07
     */
    WorkRunningUsageDTO getWorkRunningUsage(Long id);

    /**
     * Description: 列表查询运行节点
     * @return {@link List }<{@link WorkRunningUsageDTO }>
     * @author 杜黎明
     * @date 2022/12/08 15:38:59
     */
    List<WorkRunningUsageDTO> listWorkRunningUsage();

    /**
     * Description: 编辑运行节点
     * @param request 请求
     * @author 杜黎明
     * @date 2022/12/20 14:53:53
     */
    void modifyWorkRunningUsage(ModifyWorkRunningUsageRequest request);

    /**
     * Description: 删除运行节点
     * @param id id
     * @author 杜黎明
     * @date 2022/12/20 14:57:53
     */
    void deleteWorkRunningUsage(Long id);
}
