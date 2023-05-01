package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.FavorsMapper;
import com.xnnovel.xy.dao.NovelMapper;
import com.xnnovel.xy.entity.Favors;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.service.FavorsService;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.vo.FavorsVo;
import com.xnnovel.xy.vo.NovelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FavorsServiceImpl implements FavorsService {

    @Autowired
    private FavorsMapper favorsMapper;
    @Autowired
    private NovelMapper novelMapper;

    @Override
    public FavorsVo selectById(int id) {
        return favorsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FavorsVo> queryList(Map map) {
        return favorsMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return favorsMapper.queryTotal(map);
    }

    @Override
    public void save(Favors favors) {
        favors.setCreateTime(DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN));
        favorsMapper.insert(favors);
        NovelVo novel = novelMapper.selectByPrimaryKey(favors.getNovelId());
        novel.setFavors(novel.getFavors()+1);
        novelMapper.updateByPrimaryKeySelective(novel);
    }

    @Override
    public void delete(int id) {
        favorsMapper.deleteByPrimaryKey(id);
    }
}
