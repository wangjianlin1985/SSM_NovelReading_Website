package com.xnnovel.xy.controller.admin;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.entity.Comment;
import com.xnnovel.xy.entity.Notice;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/notice")
public class NoticeManagerController extends BaseController {

    /**
     * 前往公告列表页
     * @return
     */
    @RequestMapping("")
    public String toNoticeManager(){
        return "/admin/notice/list";
    }


    /**
     * 获取公告列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Page list(@RequestParam Map<String,Object> params){

        PageQuery query = new PageQuery(params);
        List<Notice> data = noticeService.queryList(query);
        int total = noticeService.queryTotal(query);
        Page page = new Page(data,total);
        return page;
    }


    /**
     * 查看公告信息
     */
    @RequestMapping("/view")
    public String view(HttpServletRequest request, ModelMap map){

        Integer id = Integer.valueOf(request.getParameter("id"));
        Notice notice = noticeService.selectById(id);
        map.put("notice",notice);
        return "admin/notice/view";
    }

    /**
     * 前往公告编辑页面
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,ModelMap map){

        String id = request.getParameter("id");
        if(!StringUtils.isEmpty(id)){

            Notice notice = noticeService.selectById(Integer.valueOf(id));
            map.put("notice",notice);
        }
        return "admin/notice/edit";
    }


    /**
     * 保存公告信息
     * @param notice
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody Notice notice){

        String date = DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN);
        notice.setCreateTime(date);
        notice.setModifyTime(date);
        notice.setStatus(1);
        noticeService.save(notice);
        R r = new R();
        r.setCode(200);
        r.setMessage("公告添加成功");
        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody Notice notice){

        noticeService.update(notice);
        R r = new R();
        r.setCode(200);
        r.setMessage("公告修改成功");
        return r;
    }


    @RequestMapping("/remove")
    @ResponseBody
    public R remove(@RequestBody Map<String,Object> map){

        Integer id = Integer.parseInt(map.get("id").toString());
        noticeService.remove(id);
        R r = new R();
        r.setCode(200);
        r.setMessage("公告删除成功");
        return r;
    }

    /**
     * 公告上下架
     * @param notice
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public R audit(@RequestBody Notice notice){

        Notice not = noticeService.selectById(notice.getId());
        not.setStatus(notice.getStatus());
        noticeService.update(not);
        R r = new R();
        r.setCode(200);
        r.setMessage("操作成功！");
        return r;
    }
}
