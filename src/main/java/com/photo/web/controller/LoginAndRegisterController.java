package com.photo.web.controller;

import com.photo.web.entity.po.MemberPO;
import com.photo.web.entity.vo.MemberLoginVo;
import com.photo.web.entity.vo.MemberRegisterVO;
import com.photo.web.service.MemberService;
import com.photo.web.util.HouseConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Create 2020-02-08 16:33
 */
@Controller
public class LoginAndRegisterController {

    @Autowired
    private MemberService memberService;

    /**
     * 前台注册
     * @param memberRegisterVO
     * @param model
     * @return
     */
    @PostMapping("/member/register")
    public String register(MemberRegisterVO memberRegisterVO, Model model) {
        //判断memberRegisterVO是否有效
        if (memberRegisterVO == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return HouseConstant.PathUrl.FRONTEND_REGISTER;
        }

        //判断loginAcct是否已存在
        String loginAcct = memberRegisterVO.getLoginacct();
        Integer countByLoginAcct = memberService.getCountByLoginAcct(loginAcct);
        if (countByLoginAcct > 0) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_REGISTER_LOGINACCT_ALREADY_EXIST);
            return HouseConstant.PathUrl.FRONTEND_REGISTER;
        }

        //若不存在进行存取
        //1.VO ==》 PO
        MemberPO memberPO = new MemberPO();
        BeanUtils.copyProperties(memberRegisterVO, memberPO);
        memberPO.setTypeId(1);
        memberPO.setIsvalid(1);
        memberPO.setSex("男");
        memberPO.setHeadpicPath("/images/默认.jpg");

        //判断注册是否成功
        try {
            memberService.saveMember(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return HouseConstant.PathUrl.FRONTEND_REGISTER;
        }

        //成功则返回登录页面，并提示注册成功
        model.addAttribute("registerResult", HouseConstant.MESSAGE_REGISTER_SUCCESS);
        return HouseConstant.PathUrl.FRONTEND_LOGIN;
    }

    /**
     * 前台登录
     * @param memberLoginVo
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/member/login")
    public String Frontendlogin(MemberLoginVo memberLoginVo, Model model, HttpSession session) {

        if (memberLoginVo == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return HouseConstant.PathUrl.FRONTEND_LOGIN;
        }

        String loginAcct = memberLoginVo.getLoginacct();
        MemberPO memberPOFromSql = memberService.login(loginAcct);

        if (memberPOFromSql == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_LOGIN_FAILED);
            return HouseConstant.PathUrl.FRONTEND_LOGIN;
        }

        if (memberPOFromSql.getIsvalid() == 0) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_MEMBER_DO_NOT_BE_USE);
            return HouseConstant.PathUrl.FRONTEND_LOGIN;
        }


        if (!Objects.equals(memberPOFromSql.getUserpswd(), memberLoginVo.getUserpswd())) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_LOGIN_FAILED);
            return HouseConstant.PathUrl.FRONTEND_LOGIN;
        }

        session.setAttribute(HouseConstant.FRONTEND_SESSION, memberPOFromSql);

        model.addAttribute("successMsg", HouseConstant.MESSAGE_LOGIN_SUCCESS);
        return "redirect:" + HouseConstant.PathUrl.FRONTEND_INDEX + "/1";
    }


    /**
     *
     * 前台退出
     * @param session
     * @return
     */
    @RequestMapping("/member/logout")
    public String Frontendlogout(HttpSession session) {
        session.removeAttribute(HouseConstant.FRONTEND_SESSION);
        return "redirect:" + HouseConstant.PathUrl.FRONTEND_INDEX + "/1";
    }

    /**
     *
     * 后台退出
     * @param session
     * @return
     */
    @RequestMapping("/backup/member/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HouseConstant.BACKUP_SESSION);
        return "redirect:" + HouseConstant.PathUrl.BACKUP_LOGIN_DERECTION;
    }

    /**
     * 后台登录
     * @param memberLoginVo
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/backup/member/login")
    public String login(MemberLoginVo memberLoginVo, Model model, HttpSession session) {

        if (memberLoginVo == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return HouseConstant.PathUrl.BACKUP_LOGIN;
        }

        String loginAcct = memberLoginVo.getLoginacct();
        MemberPO memberPOFromSql = memberService.login(loginAcct);

//        System.out.println(memberPOFromSql);
        if (memberPOFromSql == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_LOGIN_FAILED);
            return HouseConstant.PathUrl.BACKUP_LOGIN;
        }

        if(memberPOFromSql.getTypeId() != 2){
            model.addAttribute("errorMsg", HouseConstant.NOT_MANAGER);
            return HouseConstant.PathUrl.BACKUP_LOGIN;
        }

        if (memberPOFromSql.getIsvalid() == 0) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_MEMBER_DO_NOT_BE_USE);
            return HouseConstant.PathUrl.BACKUP_LOGIN;
        }


        if (!Objects.equals(memberPOFromSql.getUserpswd(), memberLoginVo.getUserpswd())) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_LOGIN_FAILED);
            return HouseConstant.PathUrl.BACKUP_LOGIN;
        }

        session.setAttribute("memberPO", memberPOFromSql);
        model.addAttribute("successMsg", HouseConstant.MESSAGE_LOGIN_SUCCESS);
        return "redirect:/backup/member/manage/1" ;
    }
}
