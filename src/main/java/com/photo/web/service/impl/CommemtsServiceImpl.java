package com.photo.web.service.impl;

import com.photo.web.entity.po.CommentsPO;
import com.photo.web.entity.po.CommentsPOCustom;
import com.photo.web.entity.po.CommentsPOExample;
import com.photo.web.mapper.CommentsPOMapper;
import com.photo.web.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Create 2020-02-11 15:58
 */
@Service
public class CommemtsServiceImpl implements CommentsService {

    @Autowired
    private CommentsPOMapper commentsPOMapper;

    @Override
    public List<CommentsPOCustom> getAllCommentWithMember() {
        CommentsPOExample commentsPOExample = new CommentsPOExample();
        commentsPOExample.setOrderByClause("create_time desc");
        return commentsPOMapper.getCommentWithMember(commentsPOExample);
    }

    @Override
    public void saveCommemts(CommentsPO commentsPO) {
        commentsPO.setCreateTime(new Date());
        commentsPOMapper.insertSelective(commentsPO);
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentsPOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void modify(CommentsPO commentsPO) {
        commentsPOMapper.updateByPrimaryKeySelective(commentsPO);
    }
}
