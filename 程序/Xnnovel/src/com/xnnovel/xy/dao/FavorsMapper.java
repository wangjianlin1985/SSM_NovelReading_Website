package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Favors;
import com.xnnovel.xy.vo.FavorsVo;

import java.util.List;
import java.util.Map;

public interface FavorsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Favors record);

    int insertSelective(Favors record);

    FavorsVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Favors record);

    int updateByPrimaryKey(Favors record);

    List<FavorsVo> queryList(Map map);

    int queryTotal(Map map);
}