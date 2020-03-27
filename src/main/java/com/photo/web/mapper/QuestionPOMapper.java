package com.photo.web.mapper;

import com.photo.web.entity.po.QuestionPO;
import com.photo.web.entity.po.QuestionPOCustom;
import com.photo.web.entity.po.QuestionPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionPOMapper {

    //
    List<QuestionPOCustom> selectWithMemberAndComCout(QuestionPOExample questionPOExample);

    QuestionPOCustom selectDetailOfEachQuestion(QuestionPOExample questionPOExample);

    QuestionPO selectByUserId(Integer userId);
    //

    int countByExample(QuestionPOExample example);

    int deleteByExample(QuestionPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionPO record);

    int insertSelective(QuestionPO record);

    List<QuestionPO> selectByExampleWithBLOBs(QuestionPOExample example);

    List<QuestionPO> selectByExample(QuestionPOExample example);

    QuestionPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionPO record, @Param("example") QuestionPOExample example);

    int updateByExampleWithBLOBs(@Param("record") QuestionPO record, @Param("example") QuestionPOExample example);

    int updateByExample(@Param("record") QuestionPO record, @Param("example") QuestionPOExample example);

    int updateByPrimaryKeySelective(QuestionPO record);

    int updateByPrimaryKeyWithBLOBs(QuestionPO record);

    int updateByPrimaryKey(QuestionPO record);

}