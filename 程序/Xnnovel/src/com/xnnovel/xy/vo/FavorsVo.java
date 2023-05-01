package com.xnnovel.xy.vo;

import com.xnnovel.xy.entity.Favors;
import com.xnnovel.xy.entity.Novel;

public class FavorsVo extends Favors {

    private Novel novel;

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }
}
