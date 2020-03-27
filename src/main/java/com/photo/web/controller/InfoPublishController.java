package com.photo.web.controller;

import com.photo.web.entity.MutipartFileUploadResult;
import com.photo.web.entity.UploadResponseResult;
import com.photo.web.entity.po.ArticlePO;
import com.photo.web.entity.po.QuestionPO;
import com.photo.web.service.ArticleService;
import com.photo.web.service.QuestionService;
import com.photo.web.service.SharingfileService;
import com.photo.web.util.FileUploadUtil;
import com.photo.web.util.HouseConstant;
import com.photo.web.util.HouseUtils;
import com.photo.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Create 2020-02-27 21:51
 */
@Controller
public class InfoPublishController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SharingfileService sharingfileService;


    /**
     * 添加文章或图片
     * @param questionPO
     * @param session
     * @return
     */
    @PostMapping("/article/add")
    @ResponseBody
    public MutipartFileUploadResult saveArticle(ArticlePO articlePO, @RequestParam(value = "headpic") MultipartFile headpic, HttpSession session){
        if(articlePO == null){
            return new MutipartFileUploadResult(0, "上传了空内容", null, null);
        }
        //时间以及userid
        articlePO.setUserId(SessionUtil.getSessionIdOfFrontend(session));
        articlePO.setCreateTime(new Date());
        //是否插入成功
        int insertCount = articleService.saveArticle(articlePO, headpic);
        if(insertCount <= 0){
            return new MutipartFileUploadResult(0, "插入失败", articlePO.getAreaId(), null);
        }
        System.out.println(articlePO.getAreaId());
        return new MutipartFileUploadResult(1, "插入成功", articlePO.getAreaId(), null);
    }

    /**
     * 添加提问
     * @param questionPO
     * @param session
     * @return
     */
    @PostMapping("/question/add")
    @ResponseBody
    public MutipartFileUploadResult saveQuestion(QuestionPO questionPO, HttpSession session){
        if(questionPO == null){
            return new MutipartFileUploadResult(0, "上传了空内容", null, null);
        }
        //时间以及userid
        questionPO.setUserId(SessionUtil.getSessionIdOfFrontend(session));
        questionPO.setCreateTime(new Date());
        //是否插入成功
        int insertCount = questionService.saveQuestion(questionPO);
        if(insertCount <= 0){
            return new MutipartFileUploadResult(0, "插入失败", null, null);
        }
        return new MutipartFileUploadResult(1, "插入成功", null, null);
    }

    /**
     * 富文本上传图片
     * @param file
     * @param model
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public UploadResponseResult uploadImage(@RequestParam(value = "file") List<MultipartFile> file, Model model){

        if(file.isEmpty()){
            return new UploadResponseResult(1, null);
        }

        ArrayList<String> pathList = new ArrayList<>();

        for (MultipartFile multipartFile : file) {
            String filePath = FileUploadUtil.saveFile(multipartFile).getModifyNamePath();
            pathList.add(filePath);
        }

        Integer errno = 0;
        model.addAttribute("pathList", pathList);
        return new UploadResponseResult(0, pathList);
    }


    /**
     * 跳转信息发布页面，初始化articleId
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/go/to/infopublish")
    public String initActivityPublish(Model model, HttpSession session){

        if(!SessionUtil.checkFrontendLogin(session)){
            model.addAttribute("errorMsg", "请先登录在进行信息发布！");
            return "/frontend/member-login";
        }

        model.addAttribute("articleId", HouseUtils.getSharingFileConnectedActicleId());

        return HouseConstant.PathUrl.FRONTEND_INFO_PUBLISH;
    }


    @PostMapping("/sharingfileupload")
    @ResponseBody
    public MutipartFileUploadResult uploadImage(@RequestParam(value = "file") List<MultipartFile> file, String articleId, Model model){

        if(!FileUploadUtil.checkFileEffect(file)){
            return new MutipartFileUploadResult(0, "上传失败！", null, null);
        }

        List<String> pathList = sharingfileService.batchSaveSharingFile(file, articleId);

        System.out.println("上传成功！");

        return new MutipartFileUploadResult(1, "上传成功！", null, pathList);
    }
}

