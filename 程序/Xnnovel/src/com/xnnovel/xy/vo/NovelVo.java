package com.xnnovel.xy.vo;

import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.entity.Type;

public class NovelVo extends Novel {

    //小说类型
    private Type type;


    //是否加入书架
    private int isInShelf;

    //是否已收藏
    private int isInFavors;

    //是否已推荐
    private int isInRecoms;

    //最新章节
    private Chapter latestChapter;


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getIsInShelf() {
        return isInShelf;
    }

    public void setIsInShelf(int isInShelf) {
        this.isInShelf = isInShelf;
    }

    public int getIsInFavors() {
        return isInFavors;
    }

    public void setIsInFavors(int isInFavors) {
        this.isInFavors = isInFavors;
    }

    public int getIsInRecoms() {
        return isInRecoms;
    }

    public void setIsInRecoms(int isInRecoms) {
        this.isInRecoms = isInRecoms;
    }

    public Chapter getLatestChapter() {
        return latestChapter;
    }

    public void setLatestChapter(Chapter latestChapter) {
        this.latestChapter = latestChapter;
    }
}
