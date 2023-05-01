package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Progress;
import com.xnnovel.xy.vo.ProgressVo;

public interface ProgressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Progress record);

    int insertSelective(Progress record);

    ProgressVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Progress record);

    int updateByPrimaryKey(Progress record);

    ProgressVo selectByNovel(int chapterId);
}