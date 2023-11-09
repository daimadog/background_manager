package com.background.manager.service;

import com.background.manager.dto.request.user.PageQueryUserEnterpriseCertificationRequest;
import com.background.manager.dto.request.user.applyUserEnterpriseCertificationRequest;
import com.background.manager.dto.response.user.UserEnterpriseCertificationDTO;
import com.background.manager.model.UserEnterpriseCertification;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @Description: 用户企业认证接口
 * @Author: 杜黎明
 * @Date: 2023/03/22 13:07:48
 * @Version: 1.0.0
 */
public interface UserEnterpriseCertificationService extends IService<UserEnterpriseCertification> {

    /**
     * Description: 运营控制台审核用户企业认证信息
     * @param request 请求
     * @author 杜黎明
     * @date 2023/03/22 13:29:00
     */
    void apply(applyUserEnterpriseCertificationRequest request);

    /**
     * Description: 分页查询用户企业认证信息
     * @param request 请求
     * @return {@link IPage }<{@link UserEnterpriseCertificationDTO }>
     * @author 杜黎明
     * @date 2023/03/22 14:05:09
     */
    IPage<UserEnterpriseCertificationDTO> listUserEnterpriseCertification(PageQueryUserEnterpriseCertificationRequest request);


}
