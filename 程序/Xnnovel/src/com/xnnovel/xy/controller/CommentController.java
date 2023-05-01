package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.Comment;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.vo.CommentVo;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/comment")
@Controller
public class CommentController extends BaseController {


    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam int novelId){

        Map map = new HashMap();
        map.put("novelId",novelId);
        List<CommentVo> commentList = commentService.queryList(map);
        R r = new R();
        r.setCode(200);
        r.setData(commentList);
        return r;
    }

    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody  Comment comment, HttpSession session){

        User user = (User) session.getAttribute("user");
        comment.setCreateTime(DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN));
        comment.setUserId(user.getId());
        comment.setStatus(1);
        commentService.save(comment);
        R r = new R();
        r.setCode(200);
        r.setMessage("评论提交成功！");
        return r;
    }
}
