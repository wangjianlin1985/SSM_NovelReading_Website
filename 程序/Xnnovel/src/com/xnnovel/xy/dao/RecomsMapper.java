package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Banner;
import com.xnnovel.xy.entity.Recoms;
import com.xnnovel.xy.vo.RecomsVo;

import java.util.List;
import java.util.Map;

public interface RecomsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recoms record);

    int insertSelective(Recoms record);

    RecomsVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recoms record);

    int updateByPrimaryKey(Recoms record);

    List<RecomsVo> queryList(Map map);

    int queryTotal(Map map);
}