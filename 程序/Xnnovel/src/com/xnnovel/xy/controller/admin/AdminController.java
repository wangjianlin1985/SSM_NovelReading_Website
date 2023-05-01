package com.xnnovel.xy.controller.admin;

import com.google.code.kaptcha.Constants;
import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.Admin;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.utils.MD5;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author：Mr.Yang
 * @date：2018/10/8 10:43
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("/login.html")
    public String tologin(HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if(admin!=null){
            return "admin/index";
        }
        return "admin/login";
    }


    @RequestMapping("/login")
    @ResponseBody
    public R login(@RequestBody Map params, HttpServletRequest request){

        R r = new R();
        String username = params.get("username").toString();
        String password = params.get("password").toString();
        String code = params.get("code").toString();
        if(!code.equalsIgnoreCase(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)+"")){
            r.setCode(500);
            r.setMessage("验证码错误！");
            return r;
        }
        Admin admin = adminService.selectByName(username);
        if(admin==null){
            r.setCode(500);
            r.setMessage("用户名不存在！");
            return r;
        }
        String encryptPwd = MD5.generatePasswordMD5(password,admin.getSalt());
        if(!encryptPwd.equals(admin.getPassword())){
            r.setCode(500);
            r.setMessage("密码错误！");
            return r;
        }
        request.getSession().setAttribute("admin",admin);
        r.setCode(200);
        r.setMessage("登录成功！");
        return r;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){

        session.removeAttribute("admin");
        return "/admin/login";

    }
}
