package com.photo.web.service.impl;

import com.photo.web.entity.po.QuestionPO;
import com.photo.web.entity.po.QuestionPOCustom;
import com.photo.web.entity.po.QuestionPOExample;
import com.photo.web.mapper.QuestionPOMapper;
import com.photo.web.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Create 2020-02-27 17:35
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionPOMapper questionPOMapper;

    @Override
    public List<QuestionPOCustom> getAllQuestionWithNoDetail() {
        QuestionPOExample questionPOExample = new QuestionPOExample();
        questionPOExample.setOrderByClause("create_time desc");
        return questionPOMapper.selectWithMemberAndComCout(questionPOExample);
    }

    @Override
    public List<QuestionPOCustom> getAllQuestionWithNoDetailBysearch(String searchContent) {
        QuestionPOExample questionPOExample = new QuestionPOExample();
        QuestionPOExample.Criteria criteria1 = questionPOExample.createCriteria();
        //标题或描述模糊
        criteria1.andDecriptionLike("%" + searchContent + "%");
        QuestionPOExample.Criteria criteria2 = questionPOExample.createCriteria().andTitleLike("%" + searchContent + "%");
        //嵌入
        questionPOExample.or(criteria2);
        questionPOExample.setOrderByClause("create_time desc");
        return questionPOMapper.selectWithMemberAndComCout(questionPOExample);
    }

    @Override
    public int saveQuestion(QuestionPO questionPO) {
        return questionPOMapper.insertSelective(questionPO);
    }

    @Override
    public QuestionPOCustom getDetailOfEachQuestion(Integer id) {
        QuestionPOExample questionPOExample = new QuestionPOExample();
        questionPOExample.createCriteria().andIdEqualTo(id);
        return questionPOMapper.selectDetailOfEachQuestion(questionPOExample);
    }

    @Override
    public Integer delete(Integer id) {

        return questionPOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer modify(QuestionPO questionPO) {

        return questionPOMapper.updateByPrimaryKeySelective(questionPO);
    }
}
