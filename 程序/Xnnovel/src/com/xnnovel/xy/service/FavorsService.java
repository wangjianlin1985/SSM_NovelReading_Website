package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Favors;
import com.xnnovel.xy.entity.Recoms;
import com.xnnovel.xy.vo.FavorsVo;

import java.util.List;
import java.util.Map;

public interface FavorsService {

    FavorsVo selectById(int id);

    List<FavorsVo> queryList(Map map);

    int queryTotal(Map map);

    void save(Favors favors);

    void delete(int id);
}
