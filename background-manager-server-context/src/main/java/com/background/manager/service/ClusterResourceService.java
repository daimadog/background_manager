package com.background.manager.service;

import com.background.manager.model.dto.request.resource.AddResourceRequest;
import com.background.manager.model.dto.request.resource.ListResourceRequest;
import com.background.manager.model.dto.request.resource.ModifyResourceRequest;
import com.background.manager.model.dto.response.resource.ResourceDigestDTO;
import com.background.manager.model.resource.ClusterResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 资源接口
 * @Author: 杜黎明
 * @Date: 2022/12/08 09:37:41
 * @Version: 1.0.0
 */
public interface ClusterResourceService extends IService<ClusterResource> {


    /**
     * Description: 新增集群资源
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/12/08 09:49:20
     */
    String add(AddResourceRequest request);

    /**
     * Description: 编辑集群资源
     * @param request 请求
     * @author 杜黎明
     * @date 2022/12/08 13:22:51
     */
    void modify(ModifyResourceRequest request);

    /**
     * Description: 删除集群资源
     * @param id id
     * @author 杜黎明
     * @date 2022/12/08 13:34:00
     */
    void delete(Long id);

    /**
     * Description: 查找指定集群资源
     * @param id 集群资源编号
     * @return {@link ResourceDigestDTO }
     * @author 杜黎明
     * @date 2022/12/08 14:31:19
     */
    ResourceDigestDTO findResource(Long id);

    /**
     * Description: 列表查询集群资源
     * @param request 请求
     * @return {@link List }<{@link ResourceDigestDTO }>
     * @author 杜黎明
     * @date 2022/12/08 17:28:29
     */
    List<ResourceDigestDTO> listQuery(ListResourceRequest request);
}
