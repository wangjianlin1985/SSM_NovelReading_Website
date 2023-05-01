package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Type;

import java.util.List;
import java.util.Map;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> queryList(Map map);

    int queryTotal(Map map);
}