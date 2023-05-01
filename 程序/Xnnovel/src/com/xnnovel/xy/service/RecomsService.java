package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Recoms;
import com.xnnovel.xy.vo.RecomsVo;

import java.util.List;
import java.util.Map;

public interface RecomsService {

    RecomsVo selectById(int id);

    List<RecomsVo> queryList(Map map);

    int queryTotal(Map map);

    void save(Recoms recoms);

    void delete(int id);
}
