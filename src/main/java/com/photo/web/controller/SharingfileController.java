package com.photo.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.photo.web.entity.po.SharingfilePO;
import com.photo.web.entity.po.SharingfilePOCustom;
import com.photo.web.service.SharingfileService;
import com.photo.web.util.HouseConstant;
import com.photo.web.util.HouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Create 2020-03-03 0:23
 */
@Controller
public class SharingfileController {

    @Autowired
    private SharingfileService sharingfileService;

    @Value("${info.pagination.pageSize}")
    private Integer pageSize;

    @GetMapping("/backup/sharingfile/manage/{pageNum}")
    public String getSharingfileByPageNumForBackup(@PathVariable("pageNum")Integer pageNum, Model model){

        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, pageSize);

        //需要放在中间才行
        List<SharingfilePOCustom> allSharingfile = sharingfileService.getAllSharingfile();

        if (!HouseUtils.collectionEffectiveCheck(allSharingfile)) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_DATA_EMPTY);
            return HouseConstant.PathUrl.BACKUP_INDEX;
        }

        PageInfo<SharingfilePOCustom> sharingfiles = new PageInfo<>(allSharingfile);

        model.addAttribute("sharingfiles", sharingfiles);
        model.addAttribute("activeId", pageNum);

        return HouseConstant.PathUrl.BACKUP_SHARINGFILE_MANAGE;
    }

    @GetMapping("/sharingfile/delete/{id}")
    public String deleteSharingfile(@PathVariable("id")Integer id, Model model){
        //非空判断
        if (id == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ID_INVALID);
            return "redirect:/backup/sharingfile/manage/1";
        }
        //删除
        try {
            sharingfileService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/sharingfile/manage/1";
        }

        return "redirect:/backup/sharingfile/manage/1";
    }

    @PostMapping("/sharingfile/modify")
    public String modifySharingfile(SharingfilePO sharingfilePO, Model model){

        if(sharingfilePO == null){
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return "redirect:/backup/sharingfile/manage/1";
        }

        try {
            sharingfileService.modify(sharingfilePO);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/sharingfile/manage/1";
        }

        return "redirect:/backup/sharingfile/manage/1";
    }

}
