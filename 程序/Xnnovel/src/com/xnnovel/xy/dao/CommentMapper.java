package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Comment;
import com.xnnovel.xy.vo.CommentVo;

import java.util.List;
import java.util.Map;

public interface CommentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    CommentVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<CommentVo> queryList(Map map);

    int queryTotal(Map map);
}