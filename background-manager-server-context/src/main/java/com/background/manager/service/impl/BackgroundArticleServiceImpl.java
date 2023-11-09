package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HtmlUtil;
import com.background.manager.config.FtpProperties;
import com.background.manager.constant.StatusConstant;
import com.background.manager.convert.BackgroundArticleConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.activity.AddArticleRequest;
import com.background.manager.model.dto.request.news.DownLoadFileRequest;
import com.background.manager.model.dto.request.news.ModifyArticleRequest;
import com.background.manager.model.dto.request.news.PageQueryArticleRequest;
import com.background.manager.model.dto.response.news.ArticleDTO;
import com.background.manager.model.dto.response.news.ArticleDigestDTO;
import com.background.manager.model.BackgroundCmsArticle;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundArticleMapper;
import com.background.manager.model.dto.response.news.ArticleHomeDigestDTO;
import com.background.manager.service.BackgroundArticleService;
import com.background.manager.service.BackgroundColumnService;
import com.background.manager.util.FileTypeCheckUtils;
import com.background.manager.util.FileTypeUtils;
import com.background.manager.util.FtpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * @Description: 新闻文章接口实现类
 * @Author: 杜黎明
 * @Date: 2022/10/25 19:51:08
 * @Version: 1.0.0
 */
@Service
public class BackgroundArticleServiceImpl extends ServiceImpl<BackgroundArticleMapper,BackgroundCmsArticle> implements BackgroundArticleService {

    @Resource
    private BackgroundArticleConvertor backgroundArticleConvertor;
    @Resource
    private BackgroundColumnService backgroundColumnService;
    @Resource
    private FtpProperties ftpProperties;

    @Override
    public String add(AddArticleRequest request) {
        //校验图片格式
        if (StringUtils.isNotBlank(request.getImgUrl())){
            FileTypeCheckUtils.checkImageFormat(request.getImgUrl());
        }
        BackgroundCmsArticle backgroundCmsArticle = backgroundArticleConvertor.toBackgroundCmsArticle(request);
        backgroundCmsArticle.setCreateTime(LocalDateTime.now());
        backgroundCmsArticle.setUpdateTime(LocalDateTime.now());
        if (ObjectUtils.isNotEmpty(StpUtil.getLoginIdDefaultNull())){
            String creator=StpUtil.getLoginIdAsString();
            backgroundCmsArticle.setCreator(creator);
            backgroundCmsArticle.setModifier(creator);
        }
        if (StatusConstant.ORIGINAL.equals(request.getIsOriginal())){
            /*处理富文本编辑内容*/
            //过滤HTML文本，防止XSS攻击 可用 会清理掉html元素标签 只留下文本
            String rteContent = HtmlUtil.escape(request.getContent());
//            html=>进行base64编码 把表情包 html的特殊转发 进行编码 数据量变为 4/3 倍
//            String encodeUrlSafe = Base64.encodeUrlSafe(request.getContent());
            backgroundCmsArticle.setContent(rteContent);
        }
        this.save(backgroundCmsArticle);
        return backgroundCmsArticle.getTitle();
    }

    @Override
    public void modify(ModifyArticleRequest request) {
        BackgroundCmsArticle backgroundCmsArticle = this.getOne(new LambdaQueryWrapper<BackgroundCmsArticle>()
                .eq(BackgroundCmsArticle::getId, request.getId()));
        if (ObjectUtil.isNull(backgroundCmsArticle)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ARTICLE_NOT_EXIST_ERROR);
        }
        if (StringUtils.isNotBlank(request.getImgUrl())){
            FileTypeCheckUtils.checkImageFormat(request.getImgUrl());
        }
        BackgroundCmsArticle modifyBackgroundCmsArticle = backgroundArticleConvertor.toBackgroundCmsArticle(request);
        modifyBackgroundCmsArticle.setUpdateTime(LocalDateTime.now());
        modifyBackgroundCmsArticle.setModifier(StpUtil.getLoginIdAsString());
        if (StatusConstant.ORIGINAL.equals(request.getIsOriginal())) {
            //处理富文本编辑器内容
            if (StringUtils.isNotBlank(request.getContent())){
                String rteContent = HtmlUtil.escape(request.getContent());
                modifyBackgroundCmsArticle.setContent(rteContent);
            }
        }
        this.updateById(modifyBackgroundCmsArticle);
    }

    @Override
    public boolean delete(Long id) {
        BackgroundCmsArticle backgroundCmsArticle = this.getOne(new LambdaQueryWrapper<BackgroundCmsArticle>()
                .eq(BackgroundCmsArticle::getId,id));
        if (ObjectUtil.isNull(backgroundCmsArticle)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ARTICLE_NOT_EXIST_ERROR);
        }
        return this.removeById(id);
    }

    @Override
    public IPage<ArticleDigestDTO> pageQuery(PageQueryArticleRequest request) {
        Page<BackgroundCmsArticle> page = this.lambdaQuery()
                .eq(ObjectUtil.isNotNull(request.getColumnId()), BackgroundCmsArticle::getColumnId, request.getColumnId())
                .orderByDesc(BackgroundCmsArticle::getArticleTime)
                .like(StringUtils.isNotBlank(request.getKeywords()), BackgroundCmsArticle::getTitle,request.getKeywords())
                .or()
                .like(StringUtils.isNotBlank(request.getKeywords()),BackgroundCmsArticle::getDescription,request.getKeywords())
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        Page<ArticleDigestDTO> pages=new Page<>(page.getCurrent(),page.getSize());
        pages.setTotal(page.getTotal());
        pages.setPages(page.getPages());
        pages.setRecords(backgroundArticleConvertor.toArticleDigestDTOS(page.getRecords()));
        return pages;
    }

    @Override
    public ArticleDTO getArticle(Long id) {
        BackgroundCmsArticle backgroundCmsArticle = this.getOne(new LambdaQueryWrapper<BackgroundCmsArticle>()
                .eq(BackgroundCmsArticle::getId,id));
        if (ObjectUtil.isNull(backgroundCmsArticle)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ARTICLE_NOT_EXIST_ERROR);
        }
        ArticleDTO articleDTO = backgroundArticleConvertor.toArticleDTO(backgroundCmsArticle);
        if (StatusConstant.ORIGINAL.equals(backgroundCmsArticle.getIsOriginal())){
            articleDTO.setContent(HtmlUtil.unescape(backgroundCmsArticle.getContent()));
        }
        return articleDTO;
    }

    @Override
    public String upload(MultipartFile  multipartFiles) {;
        String originalFilename = multipartFiles.getOriginalFilename();
        String pikId = UUID.randomUUID().toString().replaceAll("-", "");
        String fileExt = FileTypeUtils.getFileType(originalFilename);
        String newFilename = pikId +"."+fileExt;
        //生成文件在服务器端存储的子目录
        String filePath = new DateTime().toString("/yyyy/MM/dd");
        try {
            //调用FtpUtil工具进行图片的上传
            InputStream inputStream = multipartFiles.getInputStream();
            String host = ftpProperties.getHost();
            int port = ftpProperties.getPort();
            String userName = ftpProperties.getUserName();
            String passWord = ftpProperties.getPassWord();
            String basePath = ftpProperties.getBasePath();
            FtpUtil.connect(host,port,userName,passWord);
            FtpUtil.uploadFile(basePath,newFilename,inputStream);
            return newFilename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            FtpUtil.close();
        }
    }

    @Override
    public void deleteImage(String imageUrl) {
        if (StringUtils.isEmpty(imageUrl)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.MULTIPARTFILE_EMPTY_ERROR);
        }
        String host = ftpProperties.getHost();
        int port = ftpProperties.getPort();
        String userName = ftpProperties.getUserName();
        String passWord = ftpProperties.getPassWord();
        String basePath = ftpProperties.getBasePath();
        ///连接ftp服务器
        FtpUtil.connect(host,port,userName,passWord);
        FtpUtil.deleteFile(basePath,imageUrl);
        FtpUtil.close();
    }


    @Override
    public Boolean download(DownLoadFileRequest request) {
        String host = ftpProperties.getHost();
        int port = ftpProperties.getPort();
        String userName = ftpProperties.getUserName();
        String passWord = ftpProperties.getPassWord();
        String basePath = ftpProperties.getBasePath();
        ///连接ftp服务器
        FtpUtil.connect(host,port,userName,passWord);
       Boolean result= FtpUtil.download(request.getRemotePath(),request.getRemoteFileName(),request.getLocalPath(),request.getLocalFileName());
        FtpUtil.close();
        return result;

    }

    @Override
    public IPage<ArticleHomeDigestDTO> pageQueryHomeArticle(PageQueryArticleRequest request) {
        Page<BackgroundCmsArticle> page = this.lambdaQuery()
                .eq(ObjectUtil.isNotNull(request.getColumnId()), BackgroundCmsArticle::getColumnId, request.getColumnId())
                .eq(BackgroundCmsArticle::getStatus,StatusConstant.NORMAL)
                .orderByDesc(BackgroundCmsArticle::getArticleTime)
                .like(StringUtils.isNotBlank(request.getKeywords()), BackgroundCmsArticle::getTitle,request.getKeywords())
                .or()
                .like(StringUtils.isNotBlank(request.getKeywords()),BackgroundCmsArticle::getDescription,request.getKeywords())
                .page(new Page<>(request.getPage(), request.getSize()));
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        Page<ArticleHomeDigestDTO> pages=new Page<>(page.getCurrent(),page.getSize());
        pages.setTotal(page.getTotal());
        pages.setPages(page.getPages());
        pages.setRecords(backgroundArticleConvertor.toArticleHomeDigestDTOS(page.getRecords()));
        return pages;
    }

}
