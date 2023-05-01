package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.*;

import com.xnnovel.xy.vo.NovelVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/2 10:11
 */
@Controller
public class NovelController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(NovelController.class);

    @RequestMapping("/novel.html")
    public String list(HttpServletRequest request, ModelMap map){

        String typeId = request.getParameter("type");
        String currentPage = request.getParameter("pageNo");
        String title = request.getParameter("wd");
        Integer pageNo = currentPage==null?null:Integer.valueOf(currentPage);
        Integer type = (typeId==null||"".equals(typeId))?null:Integer.valueOf(typeId);
        PageQuery query = new PageQuery(pageNo);
        query.put("status",0);
        query.put("typeId",type);
        query.put("title",title);
        List<NovelVo> novelList = novelService.queryList(query);
        if(novelList!=null&&novelList.size()>0){
            for(NovelVo novel : novelList){
                Chapter latestChapter = chapterService.selectTheLatest(novel.getId());
                novel.setLatestChapter(latestChapter);
            }
        }
        int total = novelService.queryTotal(query);
        Page page = new Page(query.getPageNo(),novelList,total);
        map.put("type",type);
        map.put("page",page);
        //获取轮播图三张
        Map params = new HashMap();
        params.put("limit",3);
        List<Banner> bannerList = bannerService.queryList(params);
        map.put("bannerList",bannerList);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        params.put("start",0);
        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);
        return "novel/list";
    }

    @RequestMapping("/novel/info/{id}.html")
    public String view(HttpSession session, @PathVariable("id")Integer id, ModelMap map){

        NovelVo novel = novelService.selectById(id);
        novel.setIsInShelf(0);
        novel.setIsInFavors(0);
        novel.setIsInRecoms(0);
        //判断是否登录
        User user = (User) session.getAttribute("user");
        Map params = new HashMap();
        if(user!=null){
            params.put("novelId",id);
            params.put("userId",user.getId());
            int total = shelfService.queryTotal(params);
            if(total>0){
                novel.setIsInShelf(1);
            }
            total = recomsService.queryTotal(params);
            if(total>0){
                novel.setIsInRecoms(1);
            }
            total = favorsService.queryTotal(params);
            if(total>0){
                novel.setIsInFavors(1);
            }
        }
        map.put("novel",novel);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);
        params.put("start",0);
        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);

        //最新章节
        Chapter latestChapter = chapterService.selectTheLatest(novel.getId());
        novel.setLatestChapter(latestChapter);
        return "novel/info";

    }






}
