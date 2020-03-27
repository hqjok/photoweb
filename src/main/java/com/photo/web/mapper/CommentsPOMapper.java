package com.photo.web.mapper;

import com.photo.web.entity.po.CommentsPO;
import com.photo.web.entity.po.CommentsPOCustom;
import com.photo.web.entity.po.CommentsPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsPOMapper {

    int countByAreaIdAndArticleId(@Param("areaId") Integer areaId, @Param("belongtoId") Integer belongto);

    List<CommentsPOCustom> getCommentWithMember(CommentsPOExample example);

    List<CommentsPOCustom> getCommentList(@Param("type") int type, @Param("activityid") int activityid);

    List<CommentsPOCustom> getCommentChild(int commentId);

    int countByExample(CommentsPOExample example);

    int deleteByExample(CommentsPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentsPO record);

    int insertSelective(CommentsPO record);

    List<CommentsPO> selectByExampleWithBLOBs(CommentsPOExample example);

    List<CommentsPO> selectByExample(CommentsPOExample example);

    CommentsPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentsPO record, @Param("example") CommentsPOExample example);

    int updateByExampleWithBLOBs(@Param("record") CommentsPO record, @Param("example") CommentsPOExample example);

    int updateByExample(@Param("record") CommentsPO record, @Param("example") CommentsPOExample example);

    int updateByPrimaryKeySelective(CommentsPO record);

    int updateByPrimaryKeyWithBLOBs(CommentsPO record);

    int updateByPrimaryKey(CommentsPO record);
}