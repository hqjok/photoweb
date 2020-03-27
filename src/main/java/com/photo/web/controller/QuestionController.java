package com.photo.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.photo.web.entity.po.ArticlePO;
import com.photo.web.entity.po.ArticlePOCustom;
import com.photo.web.entity.po.QuestionPO;
import com.photo.web.entity.po.QuestionPOCustom;
import com.photo.web.service.QuestionService;
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
 * @Create 2020-02-27 17:35
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Value("${info.pagination.pageSize}")
    private Integer pageSize;

    @GetMapping("/backup/question/manage/{pageNum}")
    public String getQuestionByPageNumForBackup(@PathVariable("pageNum")Integer pageNum, Model model){

        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, pageSize);

        //需要放在中间才行
        List<QuestionPOCustom> allArticleWithMember = questionService.getAllQuestionWithNoDetail();

        if (!HouseUtils.collectionEffectiveCheck(allArticleWithMember)) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_DATA_EMPTY);
            return HouseConstant.PathUrl.BACKUP_INDEX;
        }

        PageInfo<QuestionPOCustom> questions = new PageInfo<>(allArticleWithMember);

        model.addAttribute("questions", questions);
        model.addAttribute("activeId", pageNum);

        return HouseConstant.PathUrl.BACKUP_Question_MANAGE;
    }

    @GetMapping("/question/delete/{id}")
    public String deleteQuestion(@PathVariable("id")Integer id, Model model){
        //非空判断
        if (id == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ID_INVALID);
            return "redirect:/backup/question/manage/1";
        }
        //删除
        try {
            questionService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/question/manage/1";
        }

        return "redirect:/backup/question/manage/1";
    }

    @PostMapping("/question/modify")
    public String modifyQuestion(QuestionPO questionPO,  Model model){

        if(questionPO == null){
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return "redirect:/backup/question/manage/1";
        }

        try {
            questionService.modify(questionPO);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/question/manage/1";
        }

        return "redirect:/backup/question/manage/1";
    }

    @GetMapping("/go/to/question/{pageNum}")
    public String initQuestionPage(@PathVariable("pageNum")Integer pageNum, Model model){

        //初始化pageNum
        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        //分页  文章
        PageHelper.startPage(pageNum, 20);
        List<QuestionPOCustom> allQuestionWithNoDetail = questionService.getAllQuestionWithNoDetail();
        //分页器
        PageInfo<QuestionPOCustom> questionPOPageInfo = new PageInfo<>(allQuestionWithNoDetail);
        model.addAttribute("questions", questionPOPageInfo);
        return "/frontend/frontend-question";
    }

    @GetMapping("/go/to/question/detail/{questionId}")
    public String getDetailOfQuestion(@PathVariable("questionId")Integer questionId, Model model){
        if(questionId == null){
            return "redirect:/go/to/index/1";
        }

        QuestionPOCustom detailOfEachQuestion = questionService.getDetailOfEachQuestion(questionId);
        model.addAttribute("detail", detailOfEachQuestion);

        return "/frontend/frontend-detail";
    }

}
