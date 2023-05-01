package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.NovelMapper;
import com.xnnovel.xy.dao.ShelfMapper;
import com.xnnovel.xy.entity.Shelf;
import com.xnnovel.xy.service.ShelfService;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.vo.NovelVo;
import com.xnnovel.xy.vo.ShelfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    private ShelfMapper shelfMapper;
    @Autowired
    private NovelMapper novelMapper;

    @Override
    public Shelf selectById(int id) {
        return shelfMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ShelfVo> queryList(Map map) {
        return shelfMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return shelfMapper.queryTotal(map);
    }

    @Override
    public void save(Shelf shelf) {

        shelf.setCreateTime(DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN));
        shelfMapper.insert(shelf);
    }

    @Override
    public void delete(int id) {
        shelfMapper.deleteByPrimaryKey(id);
    }
}
