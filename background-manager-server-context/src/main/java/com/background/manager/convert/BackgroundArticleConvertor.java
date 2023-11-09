package com.background.manager.convert;

import com.background.manager.model.dto.request.activity.AddArticleRequest;
import com.background.manager.model.dto.request.news.ModifyArticleRequest;
import com.background.manager.model.dto.response.news.ArticleDTO;
import com.background.manager.model.dto.response.news.ArticleDigestDTO;
import com.background.manager.model.BackgroundCmsArticle;
import com.background.manager.model.dto.response.news.ArticleHomeDigestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundArticleConvertor {

    ArticleDigestDTO toArticleDigestDTO (BackgroundCmsArticle backgroundCmsArticle);

    List<ArticleDigestDTO> toArticleDigestDTOS(List<BackgroundCmsArticle> backgroundCmsArticleList);

    @Mappings(
            @Mapping(target = "content",ignore = true)
    )
    ArticleDTO toArticleDTO(BackgroundCmsArticle backgroundCmsArticle);

    @Mappings(
            @Mapping(target = "content",ignore = true)
    )
    BackgroundCmsArticle toBackgroundCmsArticle(AddArticleRequest request);

    @Mappings(
            @Mapping(target = "content",ignore = true)
    )
    BackgroundCmsArticle toBackgroundCmsArticle(ModifyArticleRequest request);

    ArticleHomeDigestDTO toArticleHomeDigestDTO(BackgroundCmsArticle backgroundCmsArticle);

    List<ArticleHomeDigestDTO> toArticleHomeDigestDTOS(List<BackgroundCmsArticle> records);
}
