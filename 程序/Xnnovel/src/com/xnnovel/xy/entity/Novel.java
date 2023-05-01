package com.xnnovel.xy.entity;

import java.io.Serializable;
import java.util.Date;

public class Novel implements Serializable {
    private Integer id;

    //爬取数据源
    private String source;

    //小说名称
    private String title;

    private String coverUrl;

    private Integer typeId;

    private String author;

    private Integer status;

    private Integer favors;

    private Integer totalWords;

    private Integer clicks;

    private Integer recommends;

    //本章最新章节
    private Integer latest;

    //爬取的最后一个章节，对应的是数据源网站的章节ID
    private Integer latestSpider;

    private String createTime;

    private String modifyTime;

    private Integer isCompleted;

    //小说简介html内容（含标签）
    private String summary;

    //小说介绍存文本内容（去除html标签）
    private String summaryText;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFavors() {
        return favors;
    }

    public void setFavors(Integer favors) {
        this.favors = favors;
    }

    public Integer getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(Integer totalWords) {
        this.totalWords = totalWords;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getRecommends() {
        return recommends;
    }

    public void setRecommends(Integer recommends) {
        this.recommends = recommends;
    }

    public Integer getLatest() {
        return latest;
    }

    public void setLatest(Integer latest) {
        this.latest = latest;
    }

    public Integer getLatestSpider() {
        return latestSpider;
    }

    public void setLatestSpider(Integer latestSpider) {
        this.latestSpider = latestSpider;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public void setSummaryText(String summaryText) {
        this.summaryText = summaryText;
    }
}