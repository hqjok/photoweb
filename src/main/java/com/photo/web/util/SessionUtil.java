package com.photo.web.util;

import com.photo.web.entity.po.MemberPO;

import javax.servlet.http.HttpSession;

/**
 * @Create 2020-02-10 6:41
 */
public class SessionUtil {

    public static Integer getSessionIdOfFrontend(HttpSession session){
        MemberPO memberPOForFrontend = (MemberPO) session.getAttribute(HouseConstant.FRONTEND_SESSION);
        return memberPOForFrontend.getId();
    }

    public static MemberPO getMemberPOOFSessionFrontend(HttpSession session){

        return (MemberPO)session.getAttribute(HouseConstant.FRONTEND_SESSION);
    }


    public static void resetSessionFrontend_membePOCustom(MemberPO memberPO, HttpSession session){

        session.removeAttribute(HouseConstant.FRONTEND_SESSION);

        session.setAttribute(HouseConstant.FRONTEND_SESSION, memberPO);
    }


    public static boolean checkFrontendLogin(HttpSession session){
        if(session.getAttribute(HouseConstant.FRONTEND_SESSION) == null){
            return false;
        }
        return true;
    }

}
