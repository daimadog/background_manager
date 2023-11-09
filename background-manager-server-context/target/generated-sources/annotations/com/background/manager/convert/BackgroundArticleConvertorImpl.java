package com.background.manager.convert;

import com.background.manager.model.BackgroundCmsArticle;
import com.background.manager.model.dto.request.activity.AddArticleRequest;
import com.background.manager.model.dto.request.news.ModifyArticleRequest;
import com.background.manager.model.dto.response.news.ArticleDTO;
import com.background.manager.model.dto.response.news.ArticleDigestDTO;
import com.background.manager.model.dto.response.news.ArticleHomeDigestDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class BackgroundArticleConvertorImpl implements BackgroundArticleConvertor {

    @Override
    public ArticleDigestDTO toArticleDigestDTO(BackgroundCmsArticle backgroundCmsArticle) {
        if ( backgroundCmsArticle == null ) {
            return null;
        }

        ArticleDigestDTO articleDigestDTO = new ArticleDigestDTO();

        articleDigestDTO.setId( backgroundCmsArticle.getId() );
        articleDigestDTO.setTitle( backgroundCmsArticle.getTitle() );
        articleDigestDTO.setImgUrl( backgroundCmsArticle.getImgUrl() );
        articleDigestDTO.setIsOriginal( backgroundCmsArticle.getIsOriginal() );
        articleDigestDTO.setHref( backgroundCmsArticle.getHref() );
        articleDigestDTO.setCreateTime( backgroundCmsArticle.getCreateTime() );
        articleDigestDTO.setCreator( backgroundCmsArticle.getCreator() );
        articleDigestDTO.setColumnId( backgroundCmsArticle.getColumnId() );
        articleDigestDTO.setStatus( backgroundCmsArticle.getStatus() );
        articleDigestDTO.setDescription( backgroundCmsArticle.getDescription() );
        articleDigestDTO.setIsTop( backgroundCmsArticle.getIsTop() );
        articleDigestDTO.setArticleTime( backgroundCmsArticle.getArticleTime() );

        return articleDigestDTO;
    }

    @Override
    public List<ArticleDigestDTO> toArticleDigestDTOS(List<BackgroundCmsArticle> backgroundCmsArticleList) {
        if ( backgroundCmsArticleList == null ) {
            return null;
        }

        List<ArticleDigestDTO> list = new ArrayList<ArticleDigestDTO>( backgroundCmsArticleList.size() );
        for ( BackgroundCmsArticle backgroundCmsArticle : backgroundCmsArticleList ) {
            list.add( toArticleDigestDTO( backgroundCmsArticle ) );
        }

        return list;
    }

    @Override
    public ArticleDTO toArticleDTO(BackgroundCmsArticle backgroundCmsArticle) {
        if ( backgroundCmsArticle == null ) {
            return null;
        }

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setTitle( backgroundCmsArticle.getTitle() );
        articleDTO.setColumnId( backgroundCmsArticle.getColumnId() );
        articleDTO.setStatus( backgroundCmsArticle.getStatus() );
        articleDTO.setDescription( backgroundCmsArticle.getDescription() );
        articleDTO.setImgUrl( backgroundCmsArticle.getImgUrl() );
        articleDTO.setIsOriginal( backgroundCmsArticle.getIsOriginal() );
        articleDTO.setSource( backgroundCmsArticle.getSource() );
        articleDTO.setHref( backgroundCmsArticle.getHref() );
        articleDTO.setCreateTime( backgroundCmsArticle.getCreateTime() );
        articleDTO.setCreator( backgroundCmsArticle.getCreator() );
        articleDTO.setIsTop( backgroundCmsArticle.getIsTop() );
        articleDTO.setArticleTime( backgroundCmsArticle.getArticleTime() );

        return articleDTO;
    }

    @Override
    public BackgroundCmsArticle toBackgroundCmsArticle(AddArticleRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundCmsArticle backgroundCmsArticle = new BackgroundCmsArticle();

        backgroundCmsArticle.setTitle( request.getTitle() );
        backgroundCmsArticle.setImgUrl( request.getImgUrl() );
        backgroundCmsArticle.setDescription( request.getDescription() );
        backgroundCmsArticle.setIsOriginal( request.getIsOriginal() );
        backgroundCmsArticle.setHref( request.getHref() );
        backgroundCmsArticle.setSource( request.getSource() );
        backgroundCmsArticle.setIsTop( request.getIsTop() );
        backgroundCmsArticle.setColumnId( request.getColumnId() );
        backgroundCmsArticle.setArticleTime( request.getArticleTime() );

        return backgroundCmsArticle;
    }

    @Override
    public BackgroundCmsArticle toBackgroundCmsArticle(ModifyArticleRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundCmsArticle backgroundCmsArticle = new BackgroundCmsArticle();

        backgroundCmsArticle.setId( request.getId() );
        backgroundCmsArticle.setTitle( request.getTitle() );
        backgroundCmsArticle.setImgUrl( request.getImgUrl() );
        backgroundCmsArticle.setDescription( request.getDescription() );
        backgroundCmsArticle.setIsOriginal( request.getIsOriginal() );
        backgroundCmsArticle.setHref( request.getHref() );
        backgroundCmsArticle.setSource( request.getSource() );
        backgroundCmsArticle.setStatus( request.getStatus() );
        backgroundCmsArticle.setIsTop( request.getIsTop() );
        backgroundCmsArticle.setColumnId( request.getColumnId() );
        backgroundCmsArticle.setArticleTime( request.getArticleTime() );

        return backgroundCmsArticle;
    }

    @Override
    public ArticleHomeDigestDTO toArticleHomeDigestDTO(BackgroundCmsArticle backgroundCmsArticle) {
        if ( backgroundCmsArticle == null ) {
            return null;
        }

        ArticleHomeDigestDTO articleHomeDigestDTO = new ArticleHomeDigestDTO();

        articleHomeDigestDTO.setId( backgroundCmsArticle.getId() );
        articleHomeDigestDTO.setTitle( backgroundCmsArticle.getTitle() );
        articleHomeDigestDTO.setImgUrl( backgroundCmsArticle.getImgUrl() );
        articleHomeDigestDTO.setIsOriginal( backgroundCmsArticle.getIsOriginal() );
        articleHomeDigestDTO.setHref( backgroundCmsArticle.getHref() );
        articleHomeDigestDTO.setCreateTime( backgroundCmsArticle.getCreateTime() );
        articleHomeDigestDTO.setCreator( backgroundCmsArticle.getCreator() );
        articleHomeDigestDTO.setColumnId( backgroundCmsArticle.getColumnId() );
        articleHomeDigestDTO.setDescription( backgroundCmsArticle.getDescription() );
        articleHomeDigestDTO.setIsTop( backgroundCmsArticle.getIsTop() );
        articleHomeDigestDTO.setArticleTime( backgroundCmsArticle.getArticleTime() );

        return articleHomeDigestDTO;
    }

    @Override
    public List<ArticleHomeDigestDTO> toArticleHomeDigestDTOS(List<BackgroundCmsArticle> records) {
        if ( records == null ) {
            return null;
        }

        List<ArticleHomeDigestDTO> list = new ArrayList<ArticleHomeDigestDTO>( records.size() );
        for ( BackgroundCmsArticle backgroundCmsArticle : records ) {
            list.add( toArticleHomeDigestDTO( backgroundCmsArticle ) );
        }

        return list;
    }
}
