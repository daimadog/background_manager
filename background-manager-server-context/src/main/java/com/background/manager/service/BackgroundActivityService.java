package com.background.manager.service;

import com.background.manager.model.dto.request.activity.AddActivityRequest;
import com.background.manager.model.dto.request.activity.ModifyActivityRequest;
import com.background.manager.model.dto.request.activity.PageQueryActivityRequest;
import com.background.manager.model.dto.response.activity.ActivityDTO;
import com.background.manager.model.dto.response.activity.ActivityDigestDTO;
import com.background.manager.model.BackgroundCmsActivity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 后台活动管理接口
 * @Author: 杜黎明
 * @Date: 2022/10/26 19:44:21
 * @Version: 1.0.0
 */
public interface BackgroundActivityService extends IService<BackgroundCmsActivity> {

    /**
     * Description: 分页条件查询活动列表
     * @param request 请求
     * @return {@link IPage }<{@link ActivityDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/26 20:29:08
     */
    IPage<ActivityDigestDTO> pageQuery(PageQueryActivityRequest request);

    /**
     * Description: 新增活动项目
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/10/26 20:36:38
     */
    String add(AddActivityRequest request);

    /**
     * Description: 编辑活动内容
     * @param request 请求
     * @author 杜黎明
     * @date 2022/10/26 20:42:42
     */
    void modify(ModifyActivityRequest request);

    /**
     * Description: 删除活动项目
     * @param id id
     * @author 杜黎明
     * @date 2022/10/26 20:44:27
     */
    void delete(Long id);

    /**
     * Description: 查看活动详情
     * @param id id
     * @return {@link ActivityDTO }
     * @author 杜黎明
     * @date 2022/10/28 10:58:25
     */
    ActivityDTO getActivity(Long id);
}
