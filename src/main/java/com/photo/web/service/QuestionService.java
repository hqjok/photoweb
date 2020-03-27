package com.photo.web.service;

import com.photo.web.entity.po.QuestionPO;
import com.photo.web.entity.po.QuestionPOCustom;

import java.util.List;

/**
 * @Create 2020-02-27 17:35
 */
public interface QuestionService {
    List<QuestionPOCustom> getAllQuestionWithNoDetail();

    List<QuestionPOCustom> getAllQuestionWithNoDetailBysearch(String searchContent);

    int saveQuestion(QuestionPO questionPO);

    QuestionPOCustom getDetailOfEachQuestion(Integer id);

    Integer delete(Integer id);

    Integer modify(QuestionPO questionPO);
}
