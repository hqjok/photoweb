package com.photo.web.service;

import com.photo.web.entity.po.SharingfilePO;
import com.photo.web.entity.po.SharingfilePOCustom;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Create 2020-02-26 17:46
 */
public interface SharingfileService {

    List<String> batchSaveSharingFile(List<MultipartFile> fileList, String articleId);

    List<SharingfilePOCustom> getAllSharingfile();

    Integer delete(Integer id);

    Integer modify(SharingfilePO sharingfilePO);
}
