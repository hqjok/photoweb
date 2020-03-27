package com.photo.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.photo.web.entity.po.*;
import com.photo.web.service.CommentsService;
import com.photo.web.util.HouseConstant;
import com.photo.web.util.HouseUtils;
import com.photo.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Create 2020-02-11 15:06
 */
@Controller
public class CommentController {

    @Autowired
    private CommentsService commentsService;

    @Value("${info.pagination.pageSize}")
    private Integer pageSize;

    @PostMapping("/comment/add")
    public String saveComment(CommentsPO commentsPO, HttpSession session){

        if(!SessionUtil.checkFrontendLogin(session)){
            return "redirect:/go/to/login";
        }

        Integer type = commentsPO.getAreaId();
        Integer id = commentsPO.getBelongtoId();
        Integer userId = SessionUtil.getSessionIdOfFrontend(session);
        commentsPO.setUserId(userId);
        commentsService.saveCommemts(commentsPO);

        if(type == 4){
            // TODO: 2020/2/28 跳转questiondetail页面
            return "redirect:/go/to/question/detail/" + id;
        }

        return "redirect:/go/to/article/detail/" + type +"/" + id;
    }

    @GetMapping("/backup/comment/manage/{pageNum}")
    public String getCommentByPageNumForBackup(@PathVariable("pageNum")Integer pageNum, Model model){

        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<CommentsPOCustom> allComments = commentsService.getAllCommentWithMember();
        if (!HouseUtils.collectionEffectiveCheck(allComments)) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_DATA_EMPTY);
            return "/backup/go/to/index";
        }
        PageInfo<CommentsPOCustom> commentsPOCustomPageInfo = new PageInfo<>(allComments);

        model.addAttribute("comments", commentsPOCustomPageInfo);
        model.addAttribute("activeId", pageNum);

        return "/backup/comment-manager";
    }

    @PostMapping("/comment/delete/{id}")
    public String deletecomment(@PathVariable("id")Integer id, Model model){
        //非空判断
        if (id == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ID_INVALID);
            return "redirect:/backup/comment/manage/1";
        }
        //删除
        try {
            commentsService.deleteCommentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/comment/manage/1";
        }

        return "redirect:/backup/comment/manage/1";
    }

    @PostMapping("/comment/modify")
    public String modifyComment(CommentsPO commentsPO, Model model){

        if(commentsPO == null){
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return "redirect:/backup/comment/manage/1";
        }

        try {
            commentsService.modify(commentsPO);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/comment/manage/1";
        }

        return "redirect:/backup/comment/manage/1";
    }
}
