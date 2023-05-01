package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.Recoms;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/recoms")
public class RecomsController extends BaseController {

    @RequestMapping("/save")
    @ResponseBody
    public R save(HttpSession session, @RequestBody Recoms recoms){

        User user = (User) session.getAttribute("user");
        R r= new R();
        recoms.setUserId(user.getId());
        recomsService.save(recoms);
        r.setCode(200);
        r.setMessage("已推荐！");
        return r;

    }


    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Recoms recoms){

        recomsService.delete(recoms.getId());
        R r = new R();
        r.setCode(200);
        r.setMessage("取消成功！");
        return r;

    }
}
