package com.photo.web.mapper;

import com.photo.web.entity.po.SharingfilePO;
import com.photo.web.entity.po.SharingfilePOCustom;
import com.photo.web.entity.po.SharingfilePOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SharingfilePOMapper {
    //
    void batchInsert(List<SharingfilePO> sharingfilePOList);

    List<SharingfilePO> selectByArticleId(String articleId);

    List<SharingfilePOCustom> getAllSharingfileAndConnectedArticle(SharingfilePOExample example);
    //
    int countByExample(SharingfilePOExample example);

    int deleteByExample(SharingfilePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SharingfilePO record);

    int insertSelective(SharingfilePO record);

    List<SharingfilePO> selectByExample(SharingfilePOExample example);

    SharingfilePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SharingfilePO record, @Param("example") SharingfilePOExample example);

    int updateByExample(@Param("record") SharingfilePO record, @Param("example") SharingfilePOExample example);

    int updateByPrimaryKeySelective(SharingfilePO record);

    int updateByPrimaryKey(SharingfilePO record);
}