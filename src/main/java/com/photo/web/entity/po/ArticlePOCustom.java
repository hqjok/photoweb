package com.photo.web.entity.po;

import java.util.List;

/**
 * @Create 2020-02-27 16:41
 */
public class ArticlePOCustom extends ArticlePO{

    private MemberPO memberPO;
    Integer commentCount;
    List<SharingfilePO> sharingfilePOList;
    List<CommentsPOCustom> commentsPOList;

    public List<CommentsPOCustom> getCommentsPOList() {
        return commentsPOList;
    }

    public void setCommentsPOList(List<CommentsPOCustom> commentsPOList) {
        this.commentsPOList = commentsPOList;
    }

    public List<SharingfilePO> getSharingfilePOList() {
        return sharingfilePOList;
    }

    public void setSharingfilePOList(List<SharingfilePO> sharingfilePOList) {
        this.sharingfilePOList = sharingfilePOList;
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
