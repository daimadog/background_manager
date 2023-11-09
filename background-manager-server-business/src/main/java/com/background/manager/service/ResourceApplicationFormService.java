package com.background.manager.service;

import com.background.manager.dto.request.user.ResourceApplicationFormDTO;
import com.background.manager.model.ResourceApplicationForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 资源申请表接口
 * @Author: 杜黎明
 * @Date: 2023/03/13 14:42:39
 * @Version: 1.0.0
 */
public interface ResourceApplicationFormService extends IService<ResourceApplicationForm> {

    /**
     * Description: 查看资源申请表详情
     * @param userId userId
     * @return {@link ResourceApplicationFormDTO }
     * @author 杜黎明
     * @date 2023/03/13 15:00:46
     */
    ResourceApplicationFormDTO getResourceApplicationForm(String userId);


    /**
     * Description: 更新资源申请表(审核传入结论)
     * @param userId     用户id
     * @param conclusion 结论
     * @author 杜黎明
     * @date 2023/03/13 16:34:14
     */
    void modifyResourceApplicationForm(String userId, String conclusion);


}
