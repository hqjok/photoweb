package com.photo.web.mapper;

import com.photo.web.entity.po.MemberPO;
import com.photo.web.entity.po.MemberPOCustom;
import com.photo.web.entity.po.MemberPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberPOMapper {

    MemberPOCustom selectMemberAndDatailInfo(Integer userId);

    int countByExample(MemberPOExample example);

    int deleteByExample(MemberPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPO record);

    int insertSelective(MemberPO record);

    List<MemberPO> selectByExampleWithBLOBs(MemberPOExample example);

    List<MemberPO> selectByExample(MemberPOExample example);

    MemberPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPO record, @Param("example") MemberPOExample example);

    int updateByExampleWithBLOBs(@Param("record") MemberPO record, @Param("example") MemberPOExample example);

    int updateByExample(@Param("record") MemberPO record, @Param("example") MemberPOExample example);

    int updateByPrimaryKeySelective(MemberPO record);

    int updateByPrimaryKeyWithBLOBs(MemberPO record);

    int updateByPrimaryKey(MemberPO record);

}