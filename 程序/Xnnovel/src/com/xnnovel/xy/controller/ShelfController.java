package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.Shelf;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/shelf")
public class ShelfController extends BaseController {


    @RequestMapping("/save")
    @ResponseBody
    public R save(HttpSession session, @RequestBody Shelf shelf){

        User user = (User) session.getAttribute("user");
        R r= new R();
        shelf.setUserId(user.getId());
        shelfService.save(shelf);
        r.setCode(200);
        r.setMessage("已加入书架！");
        return r;

    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Shelf shelf){

        shelfService.delete(shelf.getId());
        R r = new R();
        r.setCode(200);
        r.setMessage("移除成功");
        return r;

    }

}
