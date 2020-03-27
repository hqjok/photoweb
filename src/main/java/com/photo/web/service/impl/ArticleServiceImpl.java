package com.photo.web.service.impl;

import com.photo.web.entity.po.ArticlePO;
import com.photo.web.entity.po.ArticlePOCustom;
import com.photo.web.entity.po.ArticlePOExample;
import com.photo.web.mapper.ArticlePOMapper;
import com.photo.web.service.ArticleService;
import com.photo.web.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Create 2020-02-26 19:09
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticlePOMapper articlePOMapper;

    @Override
    public List<ArticlePOCustom> getAllArticleWithNoDetailByAreaId(Integer areaId) {
        ArticlePOExample articlePOExample = new ArticlePOExample();
        articlePOExample.createCriteria().andAreaIdEqualTo(areaId);
        articlePOExample.setOrderByClause("create_time desc");
        return articlePOMapper.selectWithMemberAndComCout(articlePOExample);
    }

    @Override
    public List<ArticlePOCustom> getAllArticleWithNoDetailBysearch(Integer areaId, String searchContent) {

        ArticlePOExample articlePOExample = new ArticlePOExample();
        //对标题或描述进行模糊查询,并确认是图片还是文章类型
        ArticlePOExample.Criteria criteria2 = articlePOExample.createCriteria().andTitleLike("%" + searchContent + "%").andAreaIdEqualTo(areaId);
        ArticlePOExample.Criteria criteria3 = articlePOExample.createCriteria().andDescriptionLike("%" + searchContent + "%").andAreaIdEqualTo(areaId);
        //嵌套 (不需要嵌套criteria2 因为在第一次创建时已经嵌套进去)
        articlePOExample.or(criteria3);
        //时间倒序
        articlePOExample.setOrderByClause("create_time desc");

        return articlePOMapper.selectWithMemberAndComCout(articlePOExample);
    }

    @Override
    public int saveArticle(ArticlePO articlePO, MultipartFile headpic) {
        //上传封面并返回图片路径并存入数据库
        if(headpic != null && headpic.getSize() > 0){
            String headPicPath = FileUploadUtil.saveFile(headpic).getModifyNamePath();
            articlePO.setHeadPic(headPicPath);
        }
        return articlePOMapper.insertSelective(articlePO);
    }

    @Override
    public ArticlePOCustom getDetailOfEachArticle(Integer areaId, Integer belongtoId) {
        ArticlePOExample articlePOExample = new ArticlePOExample();
        articlePOExample.createCriteria().andAreaIdEqualTo(areaId).andIdEqualTo(belongtoId);
        return articlePOMapper.selectDetailOfEachArticle(articlePOExample);
    }

    @Override
    public Integer deleleById(Integer id) {
        return articlePOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void modify(ArticlePO articlePO) {
        articlePOMapper.updateByPrimaryKeySelective(articlePO);
    }
}
