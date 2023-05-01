package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.NovelMapper;
import com.xnnovel.xy.dao.RecomsMapper;
import com.xnnovel.xy.entity.Recoms;
import com.xnnovel.xy.service.RecomsService;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.vo.NovelVo;
import com.xnnovel.xy.vo.RecomsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RecomsServiceImpl implements RecomsService {

    @Autowired
    private RecomsMapper recomsMapper;
    @Autowired
    private NovelMapper novelMapper;

    @Override
    public RecomsVo selectById(int id) {
        return recomsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RecomsVo> queryList(Map map) {
        return recomsMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return recomsMapper.queryTotal(map);
    }

    @Override
    public void save(Recoms recoms) {
        recoms.setCreateTime(DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN));
        recomsMapper.insert(recoms);

        NovelVo novel = novelMapper.selectByPrimaryKey(recoms.getNovelId());
        novel.setRecommends(novel.getRecommends()+1);
        novelMapper.updateByPrimaryKeySelective(novel);
    }

    @Override
    public void delete(int id) {
        recomsMapper.deleteByPrimaryKey(id);
    }
}
