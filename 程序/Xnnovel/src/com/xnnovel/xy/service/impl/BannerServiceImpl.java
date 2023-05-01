package com.xnnovel.xy.service.impl;

import com.xnnovel.xy.dao.BannerMapper;
import com.xnnovel.xy.entity.Banner;
import com.xnnovel.xy.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper  bannerMapper;

    @Override
    public List<Banner> queryList(Map map) {
        return bannerMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map map) {
        return bannerMapper.queryTotal(map);
    }

    @Override
    public void save(Banner banner) {
        bannerMapper.insert(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
    }

    @Override
    public void delete(int id) {
        bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Banner selectById(int id) {
        return bannerMapper.selectByPrimaryKey(id);
    }
}
