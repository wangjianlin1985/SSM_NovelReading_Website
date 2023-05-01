package com.xnnovel.xy.base.controller;

import com.xnnovel.xy.service.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author：Mr.Yang
 * @date：2018/10/2 10:15
 */
public class BaseController {

    @Autowired
    public NovelService novelService;
    @Autowired
    public ChapterService chapterService;
    @Autowired
    public TypeService typeService;
    @Autowired
    public BannerService bannerService;
    @Autowired
    public CommentService commentService;
    @Autowired
    public ShelfService shelfService;
    @Autowired
    public RecomsService recomsService;
    @Autowired
    public FavorsService favorsService;
    @Autowired
    public UserService userService;
    @Autowired
    public NoticeService noticeService;
    @Autowired
    public AdminService adminService;
    @Autowired
    public ProgressService progressService;
}
