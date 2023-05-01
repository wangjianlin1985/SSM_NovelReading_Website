package com.xnnovel.xy.vo;

import com.xnnovel.xy.entity.Comment;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.entity.User;

public class CommentVo extends Comment {

    private User user;

    private Novel novel;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }
}
