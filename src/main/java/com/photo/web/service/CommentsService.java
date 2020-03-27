package com.photo.web.service;

import com.photo.web.entity.po.CommentsPO;
import com.photo.web.entity.po.CommentsPOCustom;

import java.util.List;

/**
 * @Create 2020-02-11 15:58
 */
public interface CommentsService {

    List<CommentsPOCustom> getAllCommentWithMember();

    void saveCommemts(CommentsPO commentsPO);

    void deleteCommentById(Integer id);

    void modify(CommentsPO commentsPO);
}
