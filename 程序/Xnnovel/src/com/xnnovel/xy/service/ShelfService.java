package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Shelf;
import com.xnnovel.xy.vo.ShelfVo;

import java.util.List;
import java.util.Map;

public interface ShelfService {

    Shelf selectById(int id);

    List<ShelfVo> queryList(Map map);

    int queryTotal(Map map);

    void save(Shelf shelf);

    void delete(int id);

}
