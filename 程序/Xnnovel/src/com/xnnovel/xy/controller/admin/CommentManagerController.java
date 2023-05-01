package com.xnnovel.xy.controller.admin;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.Comment;
import com.xnnovel.xy.vo.CommentVo;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 评论管理
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentManagerController extends BaseController {

    /**
     * 前往评论列表页
     * @return
     */
    @RequestMapping("")
    public String toCommentManager(){
        return "/admin/comment/list";
    }


    /**
     * 获取评论列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Page list(@RequestParam Map<String,Object> params){

        PageQuery query = new PageQuery(params);
        List<CommentVo> data = commentService.queryList(query);
        int total = commentService.queryTotal(query);
        Page page = new Page(data,total);
        return page;
    }


    /**
     * 获取评论详情
     * @param comment
     * @return
     */
    @RequestMapping("/getById")
    @ResponseBody
    public R getById(@RequestBody Comment comment){

        CommentVo comm = commentService.selectById(comment.getId());
        R r = new R();
        r.setCode(200);
        r.setData(comm);
        return r;
    }

    /**
     * 删除评论
     * @param comment
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public R remove(@RequestBody Comment comment){

        commentService.delete(comment.getId());
        R r = new R();
        r.setMessage("删除成功！");
        r.setCode(200);

        return r;
    }

    /**
     * 评论审核
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public R audit(@RequestBody Comment comment){

        Comment comm = commentService.selectById(comment.getId());
        comm.setStatus(comment.getStatus());
        commentService.update(comm);
        R r = new R();
        r.setCode(200);
        r.setMessage("审核成功！");
        return r;
    }
}
