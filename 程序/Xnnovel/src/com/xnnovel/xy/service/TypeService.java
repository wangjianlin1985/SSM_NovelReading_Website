package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Type;

import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/8 12:17
 */
public interface TypeService {

    public List<Type> queryList(Map map);

    int queryTotal(Map map);

    void save(Type type);

    void update(Type type);

    Type selectById(int id);

    void delete(int id);
}
