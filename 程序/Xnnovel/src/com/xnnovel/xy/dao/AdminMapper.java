package com.xnnovel.xy.dao;

import com.xnnovel.xy.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> queryList(Map map);

    int queryTotal(Map map);

    Admin selectByName(String username);


}