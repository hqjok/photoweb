package com.photo.web.entity.po;

import lombok.Data;

import java.util.List;


/**
 * @Create 2020-02-09 22:29
 */
@Data
public class MemberPOCustom extends MemberPO{

    private List<ArticlePO> imageList;
    private List<ArticlePO> articlePOList;
    private List<QuestionPO> questionPOList;

    public List<ArticlePO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ArticlePO> imageList) {
        this.imageList = imageList;
    }

    public List<ArticlePO> getArticlePOList() {
        return articlePOList;
    }

    public void setArticlePOList(List<ArticlePO> articlePOList) {
        this.articlePOList = articlePOList;
    }

    public List<QuestionPO> getQuestionPOList() {
        return questionPOList;
    }

    public void setQuestionPOList(List<QuestionPO> questionPOList) {
        this.questionPOList = questionPOList;
    }
}
