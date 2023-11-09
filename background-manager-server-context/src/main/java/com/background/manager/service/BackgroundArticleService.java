package com.background.manager.service;

import com.background.manager.model.dto.request.activity.AddArticleRequest;
import com.background.manager.model.dto.request.news.DownLoadFileRequest;
import com.background.manager.model.dto.request.news.ModifyArticleRequest;
import com.background.manager.model.dto.request.news.PageQueryArticleRequest;
import com.background.manager.model.dto.response.news.ArticleDTO;
import com.background.manager.model.dto.response.news.ArticleDigestDTO;
import com.background.manager.model.BackgroundCmsArticle;
import com.background.manager.model.dto.response.news.ArticleHomeDigestDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 新闻文章接口类
 * @Author: 杜黎明
 * @Date: 2022/10/25 19:51:26
 * @Version: 1.0.0
 */
public interface BackgroundArticleService extends IService<BackgroundCmsArticle> {

    /**
     * Description: 新闻文章的发布
     * @param request 文章发布请求体
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/10/25 20:55:35
     */
    String add(AddArticleRequest request);

    /**
     * Description: 新闻文章的编辑修改
     * @param request 编辑文章内容请求体
     * @author 杜黎明
     * @date 2022/10/26 08:47:20
     */
    void modify(ModifyArticleRequest request);

    /**
     * Description: 删除所选文章
     * @param id id
     * @return boolean
     * @author 杜黎明
     * @date 2022/10/26 09:07:09
     */
    boolean delete(Long id);

    /**
     * Description: 分页查询新闻文章
     * @param request 分页查询新闻文章请求体
     * @return IPage<ArticleDigestDTO>
     * @author 杜黎明
     * @date 2022/10/26 09:19:41
     */
    IPage<ArticleDigestDTO> pageQuery(PageQueryArticleRequest request);

    /**
     * Description: 查看文章详情
     * @param id id
     * @return {@link ArticleDTO }
     * @author 杜黎明
     * @date 2022/10/26 09:44:46
     */
    ArticleDTO getArticle(Long id);

    /**
     * Description: 上传图片文件
     * @param multipartFiles 文件
     * @return boolean
     * @author 杜黎明
     * @date 2022/11/02 10:16:50
     */
    String upload(MultipartFile multipartFiles);

    /**
     * Description: 删除服务器的图片
     * @param imageUrl 请求
     * @author 杜黎明
     * @date 2022/11/09 09:38:58
     */
    void deleteImage(String imageUrl);

    /**
     * Description: 下载文件
     * @param request 请求
     * @author 杜黎明
     * @date 2023/01/05 20:08:30
     */
    Boolean download(DownLoadFileRequest request);

    /**
     * Description: 门户网站分页查询新闻文章
     * @param request 请求
     * @return {@link IPage }<{@link ArticleHomeDigestDTO }>
     * @author 杜黎明
     * @date 2023/02/14 09:09:29
     */
    IPage<ArticleHomeDigestDTO> pageQueryHomeArticle(PageQueryArticleRequest request);
}
