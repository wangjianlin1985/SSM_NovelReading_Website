package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.NoticeMapper;
import com.xnnovel.xy.entity.Notice;
import com.xnnovel.xy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Notice selectById(int id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Notice> queryList(Map map) {
        return noticeMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return noticeMapper.queryTotal(map);
    }

    @Override
    public void save(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Override
    public void update(Notice notice) {
        noticeMapper.updateByPrimaryKeySelective(notice);
    }

    @Override
    public void remove(int id) {
        noticeMapper.deleteByPrimaryKey(id);
    }
}
