package com.photo.web.controller;

import com.photo.web.entity.MutipartFileUploadResult;
import com.photo.web.entity.UploadResponseResult;
import com.photo.web.entity.po.SharingfilePO;
import com.photo.web.mapper.SharingfilePOMapper;
import com.photo.web.util.FileUploadUtil;
import com.photo.web.util.HouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Create 2020-02-26 0:16
 */
@Controller
public class TestController {

    @Autowired
    private SharingfilePOMapper sharingfilePOMapper;

    @PostMapping("/upload1")
    @ResponseBody
    public MutipartFileUploadResult uploadImage(@RequestParam(value = "file") List<MultipartFile> file, String articleId, Model model){

        if(!FileUploadUtil.checkFileEffect(file)){
            return new MutipartFileUploadResult(0, "失败！", null, null);
        }

        ArrayList<String> pathList = new ArrayList<>();

        for (MultipartFile multipartFile : file) {
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
            sharingfilePOMapper.insertSelective(sharingfilePO);
            pathList.add("http://localhost:8080/"+ filePath);
        }

        System.out.println("上传成功！");

        return new MutipartFileUploadResult(1, "上传成功！", null, pathList);
    }
}
