package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.CommentMapper;
import com.xnnovel.xy.entity.Comment;
import com.xnnovel.xy.service.CommentService;
import com.xnnovel.xy.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<CommentVo> queryList(Map map) {
        return commentMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return commentMapper.queryTotal(map);
    }

    @Override
    public void save(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public CommentVo selectById(int id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
    }
}
