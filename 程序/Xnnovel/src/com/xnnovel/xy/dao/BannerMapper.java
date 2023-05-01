package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    List<Banner> queryList(Map map);

    int queryTotal(Map map);
}