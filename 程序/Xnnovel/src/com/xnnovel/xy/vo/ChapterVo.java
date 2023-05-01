package com.xnnovel.xy.vo;

import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.entity.Novel;

/**
 * @author：Mr.Yang
 * @date：2018/10/8 9:04
 */
public class ChapterVo extends Chapter {

    private Novel novel;

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }
}
