package com.photo.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.photo.web.entity.SearchPropery;
import com.photo.web.entity.po.ArticlePOCustom;
import com.photo.web.entity.po.QuestionPOCustom;
import com.photo.web.mapper.ArticlePOMapper;
import com.photo.web.mapper.QuestionPOMapper;
import com.photo.web.service.ArticleService;
import com.photo.web.service.QuestionService;
import com.photo.web.util.HouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Create 2020-02-27 18:35
 */
@Controller
public class SearchController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private QuestionService questionService;
    @RequestMapping("/go/to/search/{pageNum}")
    public String goSearch(SearchPropery searchPropery, @PathVariable("pageNum") Integer pageNum, Model model) {

        //初始化pageNum
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        if (searchPropery != null && searchPropery.getAreaId() != null){
            if(searchPropery.getAreaId() == 4){
                //分页  文章
                PageHelper.startPage(pageNum, 20);
                List<QuestionPOCustom> allQuestionWithNoDetail = questionService.getAllQuestionWithNoDetailBysearch(searchPropery.getSearchContent());
                //查询无结果
                if(!HouseUtils.collectionEffectiveCheck(allQuestionWithNoDetail)){
                    model.addAttribute("searchProperty", searchPropery);
                    model.addAttribute("noSearched", true);
                    return "/frontend/frontend-search";
                }
                //分页器
                PageInfo<QuestionPOCustom> questionPOPageInfo = new PageInfo<>(allQuestionWithNoDetail);
                model.addAttribute("searchs", questionPOPageInfo);
                model.addAttribute("searchProperty", searchPropery);
                model.addAttribute("noSearched", false);
                return "/frontend/frontend-search";
            }else {
                //分页  文章
                PageHelper.startPage(pageNum, 20);
                List<ArticlePOCustom> allArticleWithNoDetailBysearch = articleService.getAllArticleWithNoDetailBysearch(searchPropery.getAreaId(), searchPropery.getSearchContent());
                //查询无结果
                if(!HouseUtils.collectionEffectiveCheck(allArticleWithNoDetailBysearch)){
                    model.addAttribute("noSearched", true);
                    model.addAttribute("searchProperty", searchPropery);
                    return "/frontend/frontend-search";
                }
                //分页器
                PageInfo<ArticlePOCustom> articlePOCustomPageInfo = new PageInfo<>(allArticleWithNoDetailBysearch);
                model.addAttribute("searchs", articlePOCustomPageInfo);
                model.addAttribute("searchProperty", searchPropery);
                model.addAttribute("noSearched", false);
                return "/frontend/frontend-search";
            }

        }
        return "redirect:/go/to/index/1";
    }
}
