package com.photo.web.service.impl;

import com.photo.web.entity.po.SharingfilePO;
import com.photo.web.entity.po.SharingfilePOCustom;
import com.photo.web.entity.po.SharingfilePOExample;
import com.photo.web.mapper.SharingfilePOMapper;
import com.photo.web.service.SharingfileService;
import com.photo.web.util.FileUploadUtil;
import com.photo.web.util.HouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Create 2020-02-26 17:46
 */
@Service
public class SharingfileServiceImpl implements SharingfileService {

    @Autowired
    private SharingfilePOMapper sharingfilePOMapper;


    @Override
    public List<String> batchSaveSharingFile(List<MultipartFile> fileList, String articleId) {

        if (FileUploadUtil.checkFileEffect(fileList)){

        }

        ArrayList<String> pathList = new ArrayList<>();
        ArrayList<SharingfilePO> sharingfilePOList = new ArrayList<>();

        handFileList(fileList, articleId, pathList, sharingfilePOList);

        if(HouseUtils.collectionEffectiveCheck(sharingfilePOList)){
            sharingfilePOMapper.batchInsert(sharingfilePOList);
        }

        return pathList;

    }

    @Override
    public List<SharingfilePOCustom> getAllSharingfile() {
        SharingfilePOExample sharingfilePOExample = new SharingfilePOExample();
        sharingfilePOExample.setOrderByClause("create_time desc");
        return sharingfilePOMapper.getAllSharingfileAndConnectedArticle(sharingfilePOExample);
    }

    @Override
    public Integer delete(Integer id) {

        return sharingfilePOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer modify(SharingfilePO sharingfilePO) {
        return sharingfilePOMapper.updateByPrimaryKeySelective(sharingfilePO);
    }

    /**
     * 处理file路径以及添加file存入数据库的list
     * @param fileList
     * @param articleId
     * @param pathList
     * @param sharingfilePOList
     */
    public void handFileList(List<MultipartFile> fileList, String articleId, ArrayList<String> pathList, ArrayList<SharingfilePO> sharingfilePOList){
        for (MultipartFile multipartFile : fileList) {
            FileUploadUtil.returnResult result = FileUploadUtil.saveFile(multipartFile);
            //路径
            String filePath = result.getModifyNamePath();
            //保存数据库
            SharingfilePO sharingfilePO = new SharingfilePO();
            sharingfilePO.setCreateTime(new Date());
            sharingfilePO.setFilenname(result.getOriginName());
            sharingfilePO.setFilepath(result.getModifyNamePath());
            sharingfilePO.setFiletype(result.getFileType());
            sharingfilePO.setArticleId(articleId);
            sharingfilePOList.add(sharingfilePO);
            pathList.add("http://localhost:8080/"+ filePath);
        }
    }
}
