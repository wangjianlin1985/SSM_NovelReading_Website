package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.vo.NovelVo;

import java.util.List;
import java.util.Map;

public interface NovelMapper {
    int deleteByPrimaryKey(String id);

    int insert(Novel record);

    int insertSelective(Novel record);

    NovelVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKeyWithBLOBs(Novel record);

    int updateByPrimaryKey(Novel record);

    List<NovelVo> queryList(Map map);

    int queryTotal(Map map);

    int banchDelete(Integer[] ids);

    List<Novel> queryOrderByClick(Map map);

    List<Novel> queryOrderByRecoms(Map map);

    List<Novel> queryOrderByFavors(Map map);

}