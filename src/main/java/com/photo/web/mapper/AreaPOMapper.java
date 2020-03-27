package com.photo.web.mapper;

import com.photo.web.entity.po.AreaPO;
import com.photo.web.entity.po.AreaPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaPOMapper {
    int countByExample(AreaPOExample example);

    int deleteByExample(AreaPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AreaPO record);

    int insertSelective(AreaPO record);

    List<AreaPO> selectByExample(AreaPOExample example);

    AreaPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AreaPO record, @Param("example") AreaPOExample example);

    int updateByExample(@Param("record") AreaPO record, @Param("example") AreaPOExample example);

    int updateByPrimaryKeySelective(AreaPO record);

    int updateByPrimaryKey(AreaPO record);
}