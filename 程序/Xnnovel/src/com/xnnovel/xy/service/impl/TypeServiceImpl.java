package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.TypeMapper;
import com.xnnovel.xy.entity.Type;
import com.xnnovel.xy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/8 12:18
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> queryList(Map map) {
        return typeMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return typeMapper.queryTotal(map);
    }

    @Override
    public void save(Type type) {
        typeMapper.insert(type);
    }

    @Override
    public void update(Type type) {
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public Type selectById(int id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {
        typeMapper.deleteByPrimaryKey(id);
    }
}
