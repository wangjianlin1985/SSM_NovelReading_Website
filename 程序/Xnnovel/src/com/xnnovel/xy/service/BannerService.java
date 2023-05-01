package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {

    List<Banner> queryList(Map map);

    int queryTotal(Map map);

    void save(Banner banner);

    void update(Banner banner);

    void delete(int id);

    Banner selectById(int id);


}
