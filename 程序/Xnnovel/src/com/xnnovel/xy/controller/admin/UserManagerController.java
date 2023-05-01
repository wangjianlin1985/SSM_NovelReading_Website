package com.xnnovel.xy.controller.admin;

import com.xnnovel.xy.base.config.Global;
import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.Admin;
import com.xnnovel.xy.entity.Comment;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.utils.MD5;
import com.xnnovel.xy.utils.StringUtils;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class UserManagerController extends BaseController {

    /**
     * 前往用户列表页
     * @return
     */
    @RequestMapping("")
    public String toUserManager(){
        return "/admin/user/list";
    }


    /**
     * 获取用户列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Page list(@RequestParam Map<String,Object> params){

        PageQuery query = new PageQuery(params);
        List<User> data = userService.queryList(query);
        int total = commentService.queryTotal(query);
        Page page = new Page(data,total);
        return page;
    }


    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody User user){

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
        user.setPassword(MD5.generatePasswordMD5("123456",salt));
        userService.save(user);
        r.setCode(200);
        r.setMessage("用户添加成功！");
        return r;

    }



    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody  User user){

        R r = new R();
        User u = userService.selectById(user.getId());
        u.setNickName(user.getNickName());
        u.setEmail(user.getEmail());
        userService.update(u);
        r.setCode(200);
        r.setMessage("修改成功！");
        return r;
    }


    /**
     * 获取用户详情
     * @param user
     * @return
     */
    @RequestMapping("/getById")
    @ResponseBody
    public R getById(@RequestBody User user){

        User u = userService.selectById(user.getId());
        R r = new R();
        r.setCode(200);
        r.setData(u);
        return r;
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public R remove(@RequestBody User user){

        userService.delete(user.getId());
        R r = new R();
        r.setMessage("用户删除成功！");
        r.setCode(200);
        return r;
    }

    /**
     * 用户封禁
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public R audit(@RequestBody  User user){

        User u = userService.selectById(user.getId());
        u.setStatus(user.getStatus());
        userService.update(u);
        R r = new R();
        r.setCode(200);
        r.setMessage("操作成功！");
        return r;
    }


    /**
     * 密码重置
     * @param user
     * @return
     */
    @RequestMapping("/reset")
    @ResponseBody
    public R reset(@RequestBody User user){

        User u = userService.selectById(user.getId());
        u.setPassword(MD5.generatePasswordMD5("123456",u.getSalt()));
        userService.update(u);
        R r = new R();
        r.setMessage("读者密码重置成功！");
        r.setCode(200);
        return r;
    }
}
