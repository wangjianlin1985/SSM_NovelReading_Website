package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> queryList(Map map);

    int queryTotal(Map map);

    void save(User user);

    void update(User user);

    void delete(int id);

    User selectById(int id);

    User selectByName(String name);
}
