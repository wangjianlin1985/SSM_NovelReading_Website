package com.xnnovel.xy.entity;

import java.io.Serializable;
import java.util.Date;

public class Chapter implements Serializable {

    //章节ID
    private Integer id;
    //章节名
    private String title;
    //小说ID
    private Integer novelId;
    //创建时间
    private String createTime;
    //修改时间
    private String modifyTime;
    //章节内容（包含html标签）
    private String content;
    //章节纯文本（不包含html标签）
    private String contentText;
    //本章字数
    private Integer chapterWords;
    
    private String chapterURL = "";

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public Integer getChapterWords() {
        return chapterWords;
    }

    public void setChapterWords(Integer chapterWords) {
        this.chapterWords = chapterWords;
    }

	public String getChapterURL() {
		return chapterURL;
	}

	public void setChapterURL(String chapterURL) {
		this.chapterURL = chapterURL;
	}
    
    
}