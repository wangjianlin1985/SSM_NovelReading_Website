package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.*;
import com.xnnovel.xy.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/4 9:16
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(ChapterController.class);

    @RequestMapping("/{id}.html")
    public String list(@PathVariable("id")Integer id, ModelMap map){
        logger.info("novelId:{}",id);
        List<Chapter> chapterList = chapterService.queryByNovel(id);
        Novel novel = novelService.selectById(id);
        map.put("chapterList",chapterList);
        map.put("novel",novel);

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
        return "chapter/list";
    }

    @RequestMapping("/info/{id}.html")
    public String info(@PathVariable("id")Integer id, ModelMap map, HttpSession session){
        
        Chapter chapter = chapterService.selectById(id);

        Map params = new HashMap();
        params.put("novelId",chapter.getNovelId());
        params.put("id",chapter.getId());
        Chapter chapterPrevious = chapterService.selectPrevious(params);
        Chapter chapterNext = chapterService.selectNext(params);
        Novel novel = novelService.selectById(chapter.getNovelId());

        map.put("chapter",chapter);
        map.put("chapterPrevious",chapterPrevious);
        map.put("chapterNext",chapterNext);
        map.put("novel",novel);

        //公告
        List<Notice> noticeList = noticeService.queryList(map);
        map.put("noticeList",noticeList);
        params.put("start",0);
        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(map);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(map);
        map.put("novelListOrderByFavors",novelListOrderByFavors);

        //点击量递增
        novel.setClicks(novel.getClicks()+1);
        novelService.update(novel);

        //如果用户登录了，并且该小说被用户加入了书架，则此时记录浏览进度
        User user = (User) session.getAttribute("user");
        if(user!=null){
            Map p = new HashMap();
            p.put("novelId",chapter.getNovelId());
            p.put("userId",user.getId());
            int total = shelfService.queryTotal(p);
            if(total>0){
                Progress progress = progressService.selectByNovel(chapter.getNovelId());
                String date = DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN);
                if(progress==null){
                    progress = new Progress();
                    progress.setNovelId(chapter.getNovelId());
                    progress.setChapterId(id);
                    progress.setUserId(user.getId());
                    progress.setCreateTime(date);
                    progress.setModifyTime(date);
                    progressService.save(progress);
                }else{
                    progress.setChapterId(id);
                    progress.setModifyTime(date);
                    progressService.update(progress);
                }
            }
        }
        return "chapter/info";
    }


}
