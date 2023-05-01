package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.vo.NovelVo;

import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/2 10:06
 */
public interface NovelService {

    NovelVo selectById(Integer id);

    List<NovelVo> queryList(Map map);

    int queryTotal(Map map);

    void changeStatus(Integer id,int status);

    void save(Novel novel);

    void update(Novel novel);

    void deleteByBanch(Integer[] ids);

    List<Novel> queryOrderByClick(Map map);

    List<Novel> queryOrderByRecoms(Map map);

    List<Novel> queryOrderByFavors(Map map);
}
