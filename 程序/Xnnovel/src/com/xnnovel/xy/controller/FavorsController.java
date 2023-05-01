package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.Favors;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/favors")
public class FavorsController extends BaseController {

    @RequestMapping("/save")
    @ResponseBody
    public R save(HttpSession session, @RequestBody Favors favors){

        User user = (User) session.getAttribute("user");
        R r= new R();
        favors.setUserId(user.getId());
        favorsService.save(favors);
        r.setCode(200);
        r.setMessage("已收藏！");
        return r;

    }


    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Favors favors){

        favorsService.delete(favors.getId());
        R r = new R();
        r.setCode(200);
        r.setMessage("取消收藏成功！");
        return r;

    }
}
