package com.background.manager.service;

import com.background.manager.dto.request.log.DeleteLoginLogRequest;
import com.background.manager.dto.request.log.PageQueryLoginLogRequest;
import com.background.manager.dto.response.log.LoginLogDigestDTO;
import com.background.manager.model.BackgroundLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 登录日志实现接口类
 * @Author: 杜黎明
 * @Date: 2022/10/13 17:41:38
 * @Version: 1.0.0
 */
public interface BackgroundLoginLogService extends IService<BackgroundLoginLog> {

    /**
     * Description: 分页查询登录日志
     * @param request 分页查询登录日志请求体
     * @return {@link IPage }<{@link LoginLogDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/14 09:32:02
     */
    IPage<LoginLogDigestDTO> pageQuery(PageQueryLoginLogRequest request);

    /**
     * Description: 批量删除登录日志
     * @param request 删除登录日志请求体
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/14 10:20:21
     */
    boolean delete(DeleteLoginLogRequest request);

    /**
     * Description: 清空登录日志
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/14 10:35:43
     */
    boolean clean();

    /**
     * Description: 保存登录日志
     * @param httpServletRequest http servlet请求
     * @param loginIdAsString    登录id作为字符串
     * @param loginSuccess       登录成功
     * @author 杜黎明
     * @date 2022/10/14 13:42:26
     */
    void saveLoginLog(HttpServletRequest httpServletRequest, String loginIdAsString, Integer loginSuccess,String message);

}
