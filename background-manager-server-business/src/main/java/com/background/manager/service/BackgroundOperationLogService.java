package com.background.manager.service;

import com.background.manager.dto.request.log.DeleteOperationLogRequest;
import com.background.manager.dto.request.log.PageQueryOperationLogRequest;
import com.background.manager.dto.response.log.OperationLogDigestDTO;
import com.background.manager.model.BackgroundOperationLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BackgroundOperationLogService extends IService<BackgroundOperationLog> {

    /**
     * Description: 分页查询操作日志
     * @param request
     * @return {@link IPage }<{@link OperationLogDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/17 10:29:08
     */
    IPage<OperationLogDigestDTO> pageQuery(PageQueryOperationLogRequest request);

    /**
     * Description: 批量删除操作日志集合
     * @param request 请求
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/17 10:58:45
     */
    boolean delete(DeleteOperationLogRequest request);

    /**
     * Description: 清空操作日志
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/17 11:01:40
     */
    boolean clean();
}
