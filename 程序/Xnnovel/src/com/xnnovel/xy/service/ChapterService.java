package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.vo.ChapterVo;

import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/8 8:40
 */
public interface ChapterService {

    List<Chapter> queryByNovel(Integer novelId);
    
    Chapter queryByChapterURL(String chapterURL);

    Chapter selectById(int id);

    Chapter selectPrevious(Map map);

    Chapter selectNext(Map map);

    List<ChapterVo> queryList(Map map);

    int queryTotal(Map map);

    void save(Chapter chapter);

    void update(Chapter chapter);

    void remove(int id);

    Chapter selectTheLatest(int novelId);
}
