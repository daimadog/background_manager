package com.background.manager.service;

import com.background.manager.model.dto.request.resource.AddCustomerUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyCustomerUsageRequest;
import com.background.manager.model.dto.response.resource.CustomerUsageDTO;
import com.background.manager.model.resource.CustomerUsage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CustomerUsageService extends IService<CustomerUsage> {

    /**
     * Description: 新增客户情况请求体
     * @param request 请求
     * @author 杜黎明
     * @date 2022/12/08 22:55:32
     */
    void addCustomerUsage(AddCustomerUsageRequest request);


    /**
     * Description: 列表查询指定种类下的所有客户情况
     * @param type 类型
     * @return {@link List }<{@link CustomerUsageDTO }>
     * @author 杜黎明
     * @date 2022/12/08 23:07:46
     */
    List<CustomerUsageDTO> listCustomerUsage(Integer type);

    /**
     * Description: 删除指定编号的客户情况
     * @param id id
     * @author 杜黎明
     * @date 2022/12/20 10:53:25
     */
    void deleteCustomerUsage(Long id);

    /**
     * Description: 编辑客户情况
     * @param request 请求
     * @author 杜黎明
     * @date 2022/12/20 11:10:09
     */
    void modifyCustomerUsage(ModifyCustomerUsageRequest request);
}
