package com.photo.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.photo.web.entity.po.ArticlePO;
import com.photo.web.entity.po.ArticlePOCustom;

import com.photo.web.service.ArticleService;

import com.photo.web.util.FileUploadUtil;
import com.photo.web.util.HouseConstant;
import com.photo.web.util.HouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Create 2020-02-26 16:35
 */
@Controller
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Value("${info.pagination.pageSize}")
    private Integer pageSize;

    /**
     * 后台文章
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/backup/article/manage/{pageNum}")
    public String getArticleyPageNumForBackup(@PathVariable("pageNum")Integer pageNum, Model model){

        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, pageSize);

        //需要放在中间才行
        List<ArticlePOCustom> allArticleWithMember = articleService.getAllArticleWithNoDetailByAreaId(3);

        if (!HouseUtils.collectionEffectiveCheck(allArticleWithMember)) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_DATA_EMPTY);
            return HouseConstant.PathUrl.BACKUP_INDEX;
        }

        PageInfo<ArticlePOCustom> articles = new PageInfo<>(allArticleWithMember);

        model.addAttribute("articles", articles);
        model.addAttribute("activeId", pageNum);

        return HouseConstant.PathUrl.BACKUP_Article_MANAGE;
    }

    @GetMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable("id")Integer id, Model model){
        //非空判断
        if (id == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ID_INVALID);
            return "redirect:/backup/article/manage/1";
        }
        //删除
        try {
            articleService.deleleById(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/article/manage/1";
        }

        return "redirect:/backup/article/manage/1";
    }

    @PostMapping("/article/modify")
    public String modifyArticle(ArticlePO articlePO, @RequestParam(value = "headpic", required = false) MultipartFile file, Model model){

        if(articlePO == null){
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return "redirect:/backup/article/manage/1";
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
            return "redirect:/backup/article/manage/1";
        }

        return "redirect:/backup/article/manage/1";
    }

    /**
     * 获取全部文章
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/go/to/article/{pageNum}")
    public String initImagePage(@PathVariable("pageNum")Integer pageNum, Model model){

        //初始化pageNum
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        //分页  文章
        PageHelper.startPage(pageNum, 20);
        List<ArticlePOCustom> allArticleWithNoDetail = articleService.getAllArticleWithNoDetailByAreaId(3);
        //分页器
        PageInfo<ArticlePOCustom> articlePOPageInfo = new PageInfo<>(allArticleWithNoDetail);
        model.addAttribute("articles", articlePOPageInfo);
        return "/frontend/frontend-article";
    }

    @GetMapping("/go/to/article/detail/{areaId}/{articleId}")
    public String getDetailOfArticle(@PathVariable("areaId")Integer areaId,
                                     @PathVariable("articleId")Integer articleId,
                                     Model model){
        if(areaId == null || articleId == null){
            return "redirect:/go/to/index/1";
        }

        ArticlePOCustom detailOfEachArticle = articleService.getDetailOfEachArticle(areaId, articleId);
        model.addAttribute("detail", detailOfEachArticle);

        return "/frontend/frontend-detail";
    }
}

