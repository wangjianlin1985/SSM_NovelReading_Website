package com.xnnovel.xy.controller.admin;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.vo.ChapterVo;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/8 13:44
 */
@Controller
@RequestMapping("/admin/chapter")
public class ChapterManagerController extends BaseController {

    //跳转到小说章节列表
    @RequestMapping("")
    public String toChapterManager(HttpServletRequest requset, ModelMap map){
        String novelId = requset.getParameter("novelId");
        if(!StringUtils.isEmpty(novelId)){
            Novel novel = novelService.selectById(Integer.valueOf(novelId));
            map.put("novel",novel);
        }
        return "admin/chapter/list";
    }

    //获取小说下的所有章节
    @RequestMapping("/list")
    @ResponseBody
    public Page list(@RequestParam Map<String,Object> params){

        PageQuery query = new PageQuery(params);
        List<ChapterVo> data = chapterService.queryList(query);
        int total = chapterService.queryTotal(query);
        Page page = new Page(query.getPageNo(),data,total);
        return page;
    }

    /**
     * 查看小说章节
     */
    @RequestMapping("/view")
    public String view(HttpServletRequest request,ModelMap map){

        Integer id = Integer.valueOf(request.getParameter("id"));
        Chapter chapter = chapterService.selectById(id);
        Novel novel = novelService.selectById(chapter.getNovelId());
        map.put("novel",novel);
        map.put("chapter",chapter);
        return "admin/chapter/view";
    }

    /**
     * 前往章节编辑页面
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,ModelMap map){

        Integer novelId = Integer.valueOf(request.getParameter("novelId"));
        String chapterId = request.getParameter("chapterId");
        Novel novel = novelService.selectById(novelId);
        map.put("novel",novel);
        if(!StringUtils.isEmpty(chapterId)){

            Chapter chapter = chapterService.selectById(Integer.valueOf(chapterId));
            map.put("chapter",chapter);
        }
        return "admin/chapter/edit";
    }


    /**
     * 保存章节信息
     * @param chapter
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody Chapter chapter){

        chapterService.save(chapter);
        R r = new R();
        r.setCode(200);
        r.setMessage("章节添加成功");
        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody Chapter chapter){

        chapterService.update(chapter);
        R r = new R();
        r.setCode(200);
        r.setMessage("章节修改成功");
        return r;
    }


    @RequestMapping("/remove")
    @ResponseBody
    public R remove(@RequestBody Map<String,Object> map){

        Integer chapterId = Integer.parseInt(map.get("chapterId").toString());
        chapterService.remove(chapterId);
        R r = new R();
        r.setCode(200);
        r.setMessage("章节删除成功");
        return r;
    }


}
