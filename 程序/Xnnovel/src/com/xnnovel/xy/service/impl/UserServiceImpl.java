package com.xnnovel.xy.service.impl;


import com.xnnovel.xy.dao.UserMapper;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryList(Map map) {
        return userMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return userMapper.queryTotal(map);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
