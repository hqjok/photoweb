package com.photo.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.photo.web.entity.po.ArticlePO;
import com.photo.web.entity.po.ArticlePOCustom;
import com.photo.web.entity.po.TypePO;
import com.photo.web.entity.po.TypePOExample;
import com.photo.web.service.ArticleService;
import com.photo.web.util.FileUploadUtil;
import com.photo.web.util.HouseConstant;
import com.photo.web.util.HouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Create 2020-03-02 21:05
 */
@Controller
public class ImageController {

    @Autowired
    private ArticleService articleService;

    @Value("${info.pagination.pageSize}")
    private Integer pageSize;

    @GetMapping("/backup/image/manage/{pageNum}")
    public String getImageByPageNumForBackup(@PathVariable("pageNum")Integer pageNum, Model model){

        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, pageSize);

        //需要放在中间才行
        List<ArticlePOCustom> allArticleWithMember = articleService.getAllArticleWithNoDetailByAreaId(2);

        if (!HouseUtils.collectionEffectiveCheck(allArticleWithMember)) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_DATA_EMPTY);
            return HouseConstant.PathUrl.BACKUP_INDEX;
        }

        PageInfo<ArticlePOCustom> images = new PageInfo<>(allArticleWithMember);

        model.addAttribute("images", images);
        model.addAttribute("activeId", pageNum);

        return HouseConstant.PathUrl.BACKUP_IMAGE_MANAGE;
    }

    @GetMapping("/image/delete/{id}")
    public String deleteImage(@PathVariable("id")Integer id, Model model){
        //非空判断
        if (id == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ID_INVALID);
            return "redirect:/backup/image/manage/1";
        }
        //删除
        try {
            articleService.deleleById(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/image/manage/1";
        }

        return "redirect:/backup/image/manage/1";
    }

    @PostMapping("/image/modify")
    public String modifyImage(ArticlePO articlePO, @RequestParam(value = "headpic", required = false) MultipartFile file, Model model){

        if(articlePO == null){
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return "redirect:/backup/image/manage/1";
        }

        if (file != null){
            String filename = FileUploadUtil.saveFile(file).getModifyNamePath();
            articlePO.setHeadPic(filename);
        }

        try {
            articleService.modify(articlePO);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/image/manage/1";
        }

        return "redirect:/backup/image/manage/1";
    }
}
