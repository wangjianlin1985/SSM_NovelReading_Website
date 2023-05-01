package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.ProgressMapper;
import com.xnnovel.xy.entity.Progress;
import com.xnnovel.xy.service.ProgressService;
import com.xnnovel.xy.vo.ProgressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressMapper progressMapper;

    @Override
    public void save(Progress progress) {
        progressMapper.insert(progress);
    }

    @Override
    public void update(Progress progress) {
        progressMapper.updateByPrimaryKeySelective(progress);
    }

    @Override
    public ProgressVo selectById(int id) {
        return progressMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProgressVo selectByNovel(int novelId) {
        return progressMapper.selectByNovel(novelId);
    }
}
