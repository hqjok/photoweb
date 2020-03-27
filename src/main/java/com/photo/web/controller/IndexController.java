package com.photo.web.controller;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.photo.web.entity.po.ArticlePO;
import com.photo.web.entity.po.ArticlePOCustom;
import com.photo.web.entity.po.TypePO;
import com.photo.web.entity.po.TypePOExample;
import com.photo.web.service.ArticleService;
import com.photo.web.service.SharingfileService;
import com.photo.web.util.HouseConstant;
import com.photo.web.util.HouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * @Create 2020-02-10 22:16
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/go/to/index/{pageNum}")
    public String initIndexPage(@PathVariable("pageNum")Integer pageNum, Model model){

        //初始化pageNum
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        //分页
        PageHelper.startPage(pageNum, 20);
        List<ArticlePOCustom> allArticleWithNoDetail = articleService.getAllArticleWithNoDetailByAreaId(2);
        //分页器
        PageInfo<ArticlePOCustom> activityPOPageInfo = new PageInfo<>(allArticleWithNoDetail);
        model.addAttribute("articles", activityPOPageInfo);
        return "/frontend/index";
    }

}
