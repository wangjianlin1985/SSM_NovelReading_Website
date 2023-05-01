package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Banner;
import com.xnnovel.xy.entity.Shelf;
import com.xnnovel.xy.vo.ShelfVo;

import java.util.List;
import java.util.Map;

public interface ShelfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shelf record);

    int insertSelective(Shelf record);

    ShelfVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shelf record);

    int updateByPrimaryKey(Shelf record);

    List<ShelfVo> queryList(Map map);

    int queryTotal(Map map);
}