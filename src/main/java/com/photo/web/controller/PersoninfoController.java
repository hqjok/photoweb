package com.photo.web.controller;

import com.photo.web.entity.po.MemberPOCustom;
import com.photo.web.service.MemberService;
import com.photo.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @Create 2020-03-02 15:38
 */
@Controller
public class PersoninfoController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/go/to/personinfo")
    public String getPersonInfo(HttpSession session, Model model){

        //登录用户id
        Integer userId = SessionUtil.getSessionIdOfFrontend(session);

        //查询用户信息以及图片、文章、提问等信息
        MemberPOCustom memberAndDetailInfo = memberService.getMemberAndDetailInfo(userId);

        model.addAttribute("memberDetail", memberAndDetailInfo);

        return "/frontend/frontend-personinfo";
    }
}
