package com.photo.web.mapper;

import com.photo.web.entity.po.ArticlePO;
import com.photo.web.entity.po.ArticlePOCustom;
import com.photo.web.entity.po.ArticlePOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticlePOMapper {

    //
    List<ArticlePOCustom> selectWithMemberAndComCout(ArticlePOExample example);

    ArticlePOCustom selectDetailOfEachArticle(ArticlePOExample example);

    ArticlePO getArticleByUserId(Integer userId);

    ArticlePO getImageByUserId(Integer userId);

    ArticlePOCustom getArticleBySharingFile(Integer articleid);
    //

    int countByExample(ArticlePOExample example);

    int deleteByExample(ArticlePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticlePO record);

    int insertSelective(ArticlePO record);

    List<ArticlePO> selectByExampleWithBLOBs(ArticlePOExample example);

    List<ArticlePO> selectByExample(ArticlePOExample example);

    ArticlePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticlePO record, @Param("example") ArticlePOExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticlePO record, @Param("example") ArticlePOExample example);

    int updateByExample(@Param("record") ArticlePO record, @Param("example") ArticlePOExample example);

    int updateByPrimaryKeySelective(ArticlePO record);

    int updateByPrimaryKeyWithBLOBs(ArticlePO record);

    int updateByPrimaryKey(ArticlePO record);
}