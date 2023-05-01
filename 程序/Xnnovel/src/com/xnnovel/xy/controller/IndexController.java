package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.*;
import com.xnnovel.xy.vo.NovelVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/3 9:16
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping(value = {"","/","/index.html"})
    public String index(HttpSession session, ModelMap map){

        Map params = new HashMap();
        params.put("start",0);
        params.put("limit",10);
        List<NovelVo> novelList = novelService.queryList(params);
        if(novelList!=null&&novelList.size()>0){
            for(NovelVo novel : novelList){
                Chapter latestChapter = chapterService.selectTheLatest(novel.getId());
                novel.setLatestChapter(latestChapter);
            }
        }
        map.put("novelList",novelList);
        //获取轮播图三张
        params.put("limit",3);
        List<Banner> bannerList = bannerService.queryList(map);
        map.put("bannerList",bannerList);

        //本站排行 -- 根据点击量
        params.put("limit",5);
        List<Novel> novelListOrderByClick = novelService.queryOrderByClick(params);
        map.put("novelListOrderByClick",novelListOrderByClick);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);
        return "index";
    }

    @RequestMapping("/friendly.html")
    public String friendly(ModelMap map){

        Map params = new HashMap();
        params.put("start",0);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);
        return "friendly";
    }



    @RequestMapping("/tags.html")
    public String tags(ModelMap map){

        Map params = new HashMap();
        params.put("start",0);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);
        return "tags";
    }









}
