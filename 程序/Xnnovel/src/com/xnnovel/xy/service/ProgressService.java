package com.xnnovel.xy.service;

import com.xnnovel.xy.entity.Progress;
import com.xnnovel.xy.vo.ProgressVo;

public interface ProgressService {

    void save(Progress progress);

    void update(Progress progress);

    ProgressVo selectById(int id);

    ProgressVo selectByNovel(int novelId);
}
