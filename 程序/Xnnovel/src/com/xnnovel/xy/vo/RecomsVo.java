package com.xnnovel.xy.vo;

import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.entity.Recoms;

public class RecomsVo extends Recoms {

    private Novel novel;

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }
}
