package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.vo.ChapterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChapterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Integer id);
    
    Chapter selectByChapterURL(String chapterURL);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKeyWithBLOBs(Chapter record);

    int updateByPrimaryKey(Chapter record);

    List<Chapter> queryByNovel(@Param("novelId")Integer novelId);

    Chapter selectPrevious(Map map);

    Chapter selectNext(Map map);

    List<ChapterVo> queryList(Map map);

    int queryTotal(Map map);

    int banchDelete(Integer[] ids);

    Chapter selectTheLatest(int novelId);
}