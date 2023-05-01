package com.xnnovel.xy.vo;

import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.entity.Progress;

public class ProgressVo extends Progress {

    private Novel novel;

    private Chapter chapter;

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
