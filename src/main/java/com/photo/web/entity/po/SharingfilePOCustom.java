package com.photo.web.entity.po;

/**
 * @Create 2020-03-03 0:27
 */
public class SharingfilePOCustom extends SharingfilePO{
    ArticlePOCustom articlePO;

    public ArticlePOCustom getArticlePO() {
        return articlePO;
    }

    public void setArticlePO(ArticlePOCustom articlePO) {
        this.articlePO = articlePO;
    }
}
