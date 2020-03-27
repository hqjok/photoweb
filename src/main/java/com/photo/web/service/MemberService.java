package com.photo.web.service;

import com.photo.web.entity.po.MemberPO;
import com.photo.web.entity.po.MemberPOCustom;

import java.util.List;

/**

 * @Create 2020-01-17 8:04
 */
public interface MemberService {

    MemberPO login(String loginAcct);

    Integer getCountByLoginAcct(String loginAcct);

    void saveMember(MemberPO memberPO);

    void delete(Integer id);

    List<MemberPO> getAllMembers();

    void modify(MemberPO memberPO);

    MemberPOCustom getMemberAndDetailInfo(Integer userId);
}
