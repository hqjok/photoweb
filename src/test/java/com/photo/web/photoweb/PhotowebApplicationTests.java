package com.photo.web.photoweb;

import com.photo.web.entity.po.*;
import com.photo.web.mapper.ArticlePOMapper;
import com.photo.web.mapper.MemberPOMapper;
import com.photo.web.mapper.QuestionPOMapper;
import com.photo.web.mapper.SharingfilePOMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PhotowebApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private SharingfilePOMapper sharingfilePOMapper;

    @Test
    public void testbatchInsert(){
        SharingfilePOExample sharingfilePOExample = new SharingfilePOExample();
        sharingfilePOExample.setOrderByClause("create_time desc");
        List<SharingfilePOCustom> allSharingfileAndConnectedArticle = sharingfilePOMapper.getAllSharingfileAndConnectedArticle(sharingfilePOExample);
    }

    @Autowired
    private ArticlePOMapper articlePOMapper;

    @Test
    public void test(){
        ArticlePOExample articlePOExample = new ArticlePOExample();
        articlePOExample.createCriteria().andAreaIdEqualTo(2).andIdEqualTo(102);
        ArticlePOCustom articlePOCustoms = articlePOMapper.selectDetailOfEachArticle(articlePOExample);
    }

    @Autowired
    private QuestionPOMapper questionPOMapper;

    @Test
    public void testquestion(){
        QuestionPOExample questionPOExample = new QuestionPOExample();
        questionPOExample.createCriteria().andIdEqualTo(2);
        QuestionPOCustom questionPOCustoms = questionPOMapper.selectDetailOfEachQuestion(questionPOExample);
    }

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Test
    public void testmemberpo(){
        MemberPOCustom memberPOCustom = memberPOMapper.selectMemberAndDatailInfo(29);
        System.out.println(1);
    }
}
