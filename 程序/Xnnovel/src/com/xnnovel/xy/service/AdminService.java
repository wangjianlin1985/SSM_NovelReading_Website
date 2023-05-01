package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    List<Admin> queryList(Map map);

    int queryTotal(Map map);

    void save(Admin admin);

    void update(Admin admin);

    void delete(int id);

    Admin selectById(int id);

    Admin selectByName(String name);

}
