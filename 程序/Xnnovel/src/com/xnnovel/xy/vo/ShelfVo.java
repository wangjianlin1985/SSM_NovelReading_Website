package com.xnnovel.xy.vo;

import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.entity.Progress;
import com.xnnovel.xy.entity.Shelf;

public class ShelfVo extends Shelf {

    private NovelVo novel;

    private Progress progress;

    public NovelVo getNovel() {
        return novel;
    }

    public void setNovel(NovelVo novel) {
        this.novel = novel;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
