package com.background.manager.service;


import com.background.manager.model.dto.request.offlineRegistration.AddRegistrationRequest;
import com.background.manager.model.dto.request.offlineRegistration.PageQueryOfflineMessageRequest;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDTO;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDigestDTO;
import com.background.manager.model.BackgroundOfflineMessage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 离线登记接口类
 * @Author: 杜黎明
 * @Date: 2022/11/03 09:04:06
 * @Version: 1.0.0
 */
public interface BackgroundOfflineMessageService extends IService<BackgroundOfflineMessage> {

    /**
     * Description: 提交离线登记内容
     * @param request 请求
     * @author 杜黎明
     * @date 2022/11/03 09:24:45
     */
    void add(AddRegistrationRequest request);

    /**
     * Description: 分页查询离线登记条件
     * @param request 请求
     * @return {@link IPage }<{@link OfflineMessageDigestDTO }>
     * @author 杜黎明
     * @date 2022/11/03 09:40:25
     */
    IPage<OfflineMessageDigestDTO> pageQuery(PageQueryOfflineMessageRequest request);

    /**
     * Description: 查询离线登记详情
     * @param id id
     * @return {@link OfflineMessageDTO }
     * @author 杜黎明
     * @date 2022/11/03 09:53:26
     */
    OfflineMessageDTO getOfflineMessage(Long id);

    /**
     * Description: 处理离线登记内容
     * @param id id
     * @return boolean
     * @author 杜黎明
     * @date 2022/11/03 09:57:54
     */
    boolean process(Long id);
}


