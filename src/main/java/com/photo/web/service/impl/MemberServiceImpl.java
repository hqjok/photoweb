package com.photo.web.service.impl;

import com.photo.web.entity.po.MemberPO;
import com.photo.web.entity.po.MemberPOCustom;
import com.photo.web.entity.po.MemberPOExample;
import com.photo.web.mapper.MemberPOMapper;
import com.photo.web.service.MemberService;
import com.photo.web.util.HouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**

 * @Create 2020-01-17 8:05
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;


    @Override
    public MemberPO login(String loginAcct) {

        MemberPOExample memberPOExample = new MemberPOExample();
        memberPOExample.createCriteria().andLoginacctEqualTo(loginAcct);
        List<MemberPO> memberPOList = memberPOMapper.selectByExampleWithBLOBs(memberPOExample);
        if (!HouseUtils.collectionEffectiveCheck(memberPOList)){
            return null;
        }
        return memberPOList.get(0);

    }

    @Override
    public Integer getCountByLoginAcct(String loginAcct) {
        MemberPOExample memberPOExample = new MemberPOExample();
        memberPOExample.createCriteria().andLoginacctEqualTo(loginAcct);

        return memberPOMapper.countByExample(memberPOExample);
    }

    @Override
    public void saveMember(MemberPO memberPO) {
        memberPO.setTypeId(1);
        memberPOMapper.insertSelective(memberPO);
    }

    @Override
    public void delete(Integer id) {
        memberPOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<MemberPO> getAllMembers() {
        MemberPOExample memberPOExample = new MemberPOExample();
        memberPOExample.createCriteria().andTypeIdNotEqualTo(2);
        return memberPOMapper.selectByExampleWithBLOBs(memberPOExample);
    }

    @Override
    public void modify(MemberPO memberPO) {
//        memberPO.setTypeId(1);
        memberPOMapper.updateByPrimaryKeySelective(memberPO);
    }

    @Override
    public MemberPOCustom getMemberAndDetailInfo(Integer userId) {
        return memberPOMapper.selectMemberAndDatailInfo(userId);
    }

}
