package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.ChapterMapper;
import com.xnnovel.xy.dao.NovelMapper;
import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.service.ChapterService;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.utils.StringUtils;
import com.xnnovel.xy.vo.ChapterVo;
import com.xnnovel.xy.vo.NovelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/8 8:40
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private NovelMapper novelMapper;
    @Override
    public List<Chapter> queryByNovel(Integer novelId) {
        return chapterMapper.queryByNovel(novelId);
    }

    @Override
    public Chapter selectById(int id) {
        return chapterMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Chapter queryByChapterURL(String chapterURL) {
        return chapterMapper.selectByChapterURL(chapterURL);
    }
    
    

    @Override
    public Chapter selectPrevious(Map map) {
        return chapterMapper.selectPrevious(map);
    }

    @Override
    public Chapter selectNext(Map map) {
        return chapterMapper.selectNext(map);
    }

    @Override
    public List<ChapterVo> queryList(Map map) {
        return chapterMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return chapterMapper.queryTotal(map);
    }

    /**
     * 章节保存
     * @param chapter
     */
    @Transactional
    @Override
    public void save(Chapter chapter) {

        int chapterWords = StringUtils.getTotalWordsCount(chapter.getContentText());
        chapter.setChapterWords(chapterWords);
        chapter.setCreateTime(DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN));
        chapterMapper.insert(chapter);
        //小说字数统计
        NovelVo novel = novelMapper.selectByPrimaryKey(chapter.getNovelId());
        novel.setTotalWords(novel.getTotalWords()+chapterWords);
        novelMapper.updateByPrimaryKey(novel);
    }

    @Override
    public void update(Chapter chapter) {

        Chapter c = chapterMapper.selectByPrimaryKey(chapter.getId());
        c.setTitle(chapter.getTitle());
        c.setContent(chapter.getContent());
        chapterMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public void remove(int id) {

        chapterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Chapter selectTheLatest(int novelId) {
        return chapterMapper.selectTheLatest(novelId);
    }
}
