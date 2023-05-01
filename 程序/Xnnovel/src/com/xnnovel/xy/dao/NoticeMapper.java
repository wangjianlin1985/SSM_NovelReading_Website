package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> queryList(Map map);

    int queryTotal(Map map);
}