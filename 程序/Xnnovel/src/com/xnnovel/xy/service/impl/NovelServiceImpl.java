package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.ChapterMapper;
import com.xnnovel.xy.dao.NovelMapper;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.service.NovelService;
import com.xnnovel.xy.vo.NovelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/2 10:06
 */
@Service
public class NovelServiceImpl implements NovelService {

    @Autowired
    private NovelMapper novelMapper;
    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public NovelVo selectById(Integer id){
        return novelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<NovelVo> queryList(Map map) {
        return novelMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return novelMapper.queryTotal(map);
    }

    @Override
    public void changeStatus(Integer id, int status) {

        Novel novel = novelMapper.selectByPrimaryKey(id);
        novel.setStatus(status);
        novelMapper.updateByPrimaryKeySelective(novel);

    }

    @Override
    public void save(Novel novel) {
        novelMapper.insertSelective(novel);
    }

    @Override
    public void update(Novel novel) {
        novelMapper.updateByPrimaryKeySelective(novel);
    }

    @Override
    public void deleteByBanch(Integer[] ids) {
        //删除小说
        novelMapper.banchDelete(ids);
        //删除章节
        chapterMapper.banchDelete(ids);
    }

    @Override
    public List<Novel> queryOrderByClick(Map map) {
        return novelMapper.queryOrderByClick(map);
    }

    @Override
    public List<Novel> queryOrderByRecoms(Map map) {
        return novelMapper.queryOrderByRecoms(map);
    }

    @Override
    public List<Novel> queryOrderByFavors(Map map) {
        return novelMapper.queryOrderByFavors(map);
    }

}
