package com.photo.web.entity.po;

import lombok.Data;

import java.util.List;

/**
 * @Create 2020-02-11 1:30
 */
@Data
public class CommentsPOCustom extends CommentsPO{
    private MemberPO memberPO;
    private List<CommentsPOCustom> commentsPOList;
    private AreaPO areaPO;

    public MemberPO getMemberPO() {
        return memberPO;
    }

    public void setMemberPO(MemberPO memberPO) {
        this.memberPO = memberPO;
    }

    public List<CommentsPOCustom> getCommentsPOList() {
        return commentsPOList;
    }

    public void setCommentsPOList(List<CommentsPOCustom> commentsPOList) {
        this.commentsPOList = commentsPOList;
    }

    public AreaPO getAreaPO() {
        return areaPO;
    }

    public void setAreaPO(AreaPO areaPO) {
        this.areaPO = areaPO;
    }
}
