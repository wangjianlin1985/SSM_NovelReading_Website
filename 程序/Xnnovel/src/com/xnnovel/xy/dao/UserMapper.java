package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryList(Map map);

    int queryTotal(Map map);

    User selectByName(String userName);
}