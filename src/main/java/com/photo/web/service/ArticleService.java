package com.photo.web.service;

import com.photo.web.entity.po.ArticlePO;
import com.photo.web.entity.po.ArticlePOCustom;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Create 2020-02-26 19:08
 */
public interface ArticleService {
    List<ArticlePOCustom> getAllArticleWithNoDetailByAreaId(Integer areaId);

    List<ArticlePOCustom> getAllArticleWithNoDetailBysearch(Integer areaId, String searchContent);

    int saveArticle(ArticlePO articlePO, MultipartFile headpic);

    ArticlePOCustom getDetailOfEachArticle(Integer areaId, Integer belongtoId);

    Integer deleleById(Integer id);

    void modify(ArticlePO articlePO);
}
