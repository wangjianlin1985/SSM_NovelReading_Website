package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Comment;
import com.xnnovel.xy.vo.CommentVo;

import java.util.List;
import java.util.Map;

public interface CommentService {

    List<CommentVo> queryList(Map map);

    int queryTotal(Map map);

    void save(Comment comment);

    CommentVo selectById(int id);

    void delete(int id);

    void update(Comment comment);
}
