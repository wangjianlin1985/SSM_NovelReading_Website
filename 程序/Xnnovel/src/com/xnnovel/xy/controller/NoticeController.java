package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.entity.Notice;
import com.xnnovel.xy.entity.Novel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {


    @RequestMapping("/info/{id}.html")
    public String info(@PathVariable("id")Integer id, ModelMap map){

        Notice notice = noticeService.selectById(id);
        map.put("notice",notice);

        //公告
        List<Notice> noticeList = noticeService.queryList(map);
        map.put("noticeList",noticeList);
        Map params = new HashMap();
        params.put("start",0);
        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(map);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(map);
        map.put("novelListOrderByFavors",novelListOrderByFavors);
        return "notice/info";
    }
}
