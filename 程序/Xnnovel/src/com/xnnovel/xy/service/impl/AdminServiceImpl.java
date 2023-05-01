package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.AdminMapper;
import com.xnnovel.xy.entity.Admin;
import com.xnnovel.xy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> queryList(Map map) {
        return adminMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return adminMapper.queryTotal(map);
    }

    @Override
    public void save(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void delete(int id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin selectById(int id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin selectByName(String name) {
        return adminMapper.selectByName(name);
    }


}
