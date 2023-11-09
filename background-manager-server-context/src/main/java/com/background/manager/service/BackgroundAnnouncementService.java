package com.background.manager.service;

import com.background.manager.model.BackgroundAnnouncement;
import com.background.manager.model.dto.request.announcement.AddAnnouncementRequest;
import com.background.manager.model.dto.request.announcement.ModifyAnnouncementRequest;
import com.background.manager.model.dto.request.announcement.PageQueryAnnouncementRequest;
import com.background.manager.model.dto.response.announcement.BackgroundAnnouncementDigestDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @Description: 公告管理接口
 * @Author: 杜黎明
 * @Date: 2023/02/23 13:51:45
 * @Version: 1.0.0
 */
public interface BackgroundAnnouncementService extends IService<BackgroundAnnouncement> {

    /**
     * Description: 新增公告
     * @param request  新增公告请求体
     * @return {@link String }
     * @author 杜黎明
     * @date 2023/02/23 14:00:45
     */
    String add(AddAnnouncementRequest request);

    /**
     * Description: 分页查询公告列表
     * @param request 请求
     * @return {@link IPage }<{@link BackgroundAnnouncementDigestDTO }>
     * @author 杜黎明
     * @date 2023/02/23 14:35:16
     */
    IPage<BackgroundAnnouncementDigestDTO> pageQuery(PageQueryAnnouncementRequest request);

    /**
     * Description: 编辑公告(发布/取消发布)
     * @param request 请求
     * @author 杜黎明
     * @date 2023/02/23 14:49:40
     */
    void edit(ModifyAnnouncementRequest request);

    /**
     * Description: 删除公告
     * @param id id
     * @author 杜黎明
     * @date 2023/02/23 14:54:00
     */
    void delete(Long id);
}
