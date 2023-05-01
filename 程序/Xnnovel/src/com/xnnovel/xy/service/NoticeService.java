package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    Notice selectById(int id);

    List<Notice> queryList(Map map);

    int queryTotal(Map map);

    void save(Notice notice);

    void update(Notice notice);

    void remove(int id);
}
