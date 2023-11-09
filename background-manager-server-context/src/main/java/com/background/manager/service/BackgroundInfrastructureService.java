package com.background.manager.service;

import com.background.manager.model.BackgroundInfrastructure;
import com.background.manager.model.dto.request.style.ListInfrastructureRequest;
import com.background.manager.model.dto.request.style.ModifyInfrastructureRequest;
import com.background.manager.model.dto.response.style.BackgroundInfrastructureDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BackgroundInfrastructureService extends IService<BackgroundInfrastructure> {


    /**
     * Description: 列表查询基础设施
     * @param request 请求
     * @return {@link List }<{@link BackgroundInfrastructureDTO }>
     * @author 杜黎明
     * @date 2023/02/10 14:08:50
     */
    List<BackgroundInfrastructureDTO> listInfrastructure(ListInfrastructureRequest request);

    /**
     * Description: 修改基础设施
     * @param request 请求
     * @author 杜黎明
     * @date 2023/02/10 14:30:07
     */
    void modifyInfrastructure(ModifyInfrastructureRequest request);
}
