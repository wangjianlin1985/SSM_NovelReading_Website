package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.utils.MD5;
import com.xnnovel.xy.utils.StringUtils;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class LoginController extends BaseController {

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }


    @RequestMapping("/register.html")
    public String toRegiter(){
        return "register";
    }


    /**
     * 登录验证
     * @param user
     * @return
     */
    @RequestMapping("/signin")
    @ResponseBody
    public R signIn(@RequestBody User user, HttpSession session){

        R r = new R();
        User u = userService.selectByName(user.getUserName());
        if(u==null){
            r.setCode(500);
            r.setMessage("用户名不存在！");
            return r;
        }
        if(!u.getPassword().equals(MD5.generatePasswordMD5(user.getPassword(),u.getSalt()))){
            r.setCode(500);
            r.setMessage("密码错误！");
            return r;
        }
        session.setAttribute("user",u);
        r.setCode(200);
        r.setMessage("登录成功！");
        r.setData(u);
        return r;
    }


    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/signup")
    @ResponseBody
    public R signup(@RequestBody User user){

        R r = new R();

        User u = userService.selectByName(user.getUserName());
        if(u!=null){
            r.setCode(500);
            r.setMessage("用户名已存在！");
            return r;
        }
        Date date = new Date();
        String salt = StringUtils.getSalt();
        user.setCreateTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        user.setModifyTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        //正常
        user.setStatus(1);
        user.setSalt(salt);
        user.setPassword(MD5.generatePasswordMD5(user.getPassword(),salt));
        userService.save(user);
        r.setCode(200);
        r.setMessage("注册成功，欢迎加入牛码小说网！");
        return r;

    }

    @RequestMapping("/signout")
    public String signout(HttpSession session){

        session.removeAttribute("user");
        return "redirect:/index.html";

    }


}
