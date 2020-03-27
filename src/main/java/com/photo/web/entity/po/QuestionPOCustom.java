package com.photo.web.entity.po;

import java.util.List;

/**
 * @Create 2020-02-27 17:42
 */
public class QuestionPOCustom extends QuestionPO{
    private MemberPO memberPO;
    Integer commentCount;
    List<CommentsPOCustom> commentsPOList;

    public List<CommentsPOCustom> getCommentsPOList() {
        return commentsPOList;
    }

    public void setCommentsPOList(List<CommentsPOCustom> commentsPOList) {
        this.commentsPOList = commentsPOList;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public MemberPO getMemberPO() {
        return memberPO;
    }

    public void setMemberPO(MemberPO memberPO) {
        this.memberPO = memberPO;
    }
}
