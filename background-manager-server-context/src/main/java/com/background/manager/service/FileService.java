package com.background.manager.service;

import com.background.manager.model.dto.request.news.DownLoadFileRequest;
import com.background.manager.model.dto.request.news.DownLoadRequest;
import com.background.manager.model.dto.request.news.UploadFileRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description: 文件处理类接口
 * @Author: 杜黎明
 * @Date: 2023/01/06 14:23:53
 * @Version: 1.0.0
 */
public interface FileService {

    /**
     * Description: 上传文件到本地文件夹
     * @param multipartFiles 文件
     * @param dirPath 保存目录地址
     * @return {@link String }
     * @author 杜黎明
     * @date 2023/02/18 10:38:34
     */
    String upload(MultipartFile multipartFiles,String  dirPath);

    /**
     * Description: 下载文件
     * @param request 请求
     * @author 杜黎明
     * @date 2023/02/18 11:38:19
     */
    boolean download(DownLoadRequest request);

    /**
     * Description: 删除文件
     * @param imageUrl 图像url
     * @return boolean
     * @author 杜黎明
     * @date 2023/02/18 13:03:15
     */
    boolean delete(String imageUrl);

}
