package com.photo.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.photo.web.entity.po.MemberPO;
import com.photo.web.entity.po.MemberPOCustom;
import com.photo.web.service.MemberService;
import com.photo.web.util.FileUploadUtil;
import com.photo.web.util.HouseConstant;
import com.photo.web.util.HouseUtils;
import com.photo.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Create 2020-01-17 8:15
 */
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Value("${info.pagination.pageSize}")
    private Integer pageSize;

    @PostMapping("/member/upload")
    public String saveHeadPic(@RequestParam(value = "file") MultipartFile file, Model model, HttpSession session
    ){

        if(file.isEmpty()){
            model.addAttribute("errorMsg", "上传失败");
            return "/frontend/frontend-personinfo";
        }

        String imagePath = FileUploadUtil.saveFile(file).getModifyNamePath();

        //session中的memberPO
        Integer userId = SessionUtil.getSessionIdOfFrontend(session);

        MemberPO memberPO = new MemberPO();
        memberPO.setHeadpicPath(imagePath);
        memberPO.setId(userId);

        memberService.modify(memberPO);

        return "redirect:/go/to/personinfo";
    }

    @PostMapping("/member/add")
    @ResponseBody
    public String addMember(MemberPO memberPO, Model model){

        if(memberPO == null){
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return "true";
        }

        try {
            memberService.saveMember(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
        }

        return "true";
    }

    @PostMapping("/member/modify")
    public String modifyMember(MemberPO memberPO, Model model){

        if(memberPO == null){
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return "redirect:/backup/member/manage/1";
        }

        try {
            memberService.modify(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/member/manage/1";
        }

        return "redirect:/backup/member/manage/1";
    }

    @PostMapping("/frontend/member/modify")
    public String modifyMemberByFrontend(MemberPO memberPO, Model model){

        if(memberPO == null){
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_INPUT_INVALID);
            return "redirect:/go/to/personinfo";
        }

        try {
            memberService.modify(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/go/to/personinfo";
        }

        return "redirect:/go/to/personinfo";
    }

    @RequestMapping("/member/delete/{id}")
    public String deleteMember(@PathVariable("id") Integer id, Model model) {
        //非空判断
        if (id == null) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ID_INVALID);
            return "redirect:/backup/member/manage/1";
        }
        //删除
        try {
            memberService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_ERROR_INTERNAL);
            return "redirect:/backup/member/manage/1";
        }

        return "redirect:/backup/member/manage/1";
    }

    @RequestMapping("/backup/member/manage/{pageNum}")
    public String getMembersByPageNum(@PathVariable("pageNum")Integer pageNum, Model model) {

        if(pageNum == null || pageNum == 0){
            pageNum = 1;
        }



        PageHelper.startPage(pageNum, pageSize);

        //需要放在中间才行
        List<MemberPO> allMembers = memberService.getAllMembers();

        if (!HouseUtils.collectionEffectiveCheck(allMembers)) {
            model.addAttribute("errorMsg", HouseConstant.MESSAGE_DATA_EMPTY);
            return HouseConstant.PathUrl.BACKUP_INDEX;
        }

        PageInfo<MemberPO> memberPOPageInfo = new PageInfo<>(allMembers);

        model.addAttribute("members", memberPOPageInfo);
        model.addAttribute("activeId", pageNum);

        return HouseConstant.PathUrl.BACKUP_MEMBER_MANAGE;

    }
}
