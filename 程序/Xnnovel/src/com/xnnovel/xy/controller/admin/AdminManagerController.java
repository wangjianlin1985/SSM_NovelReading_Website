package com.xnnovel.xy.controller.admin;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.Admin;
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
@RequestMapping("/admin/admin")
public class AdminManagerController extends BaseController {

    /**
     * 前往用户列表页
     * @return
     */
    @RequestMapping("")
    public String toAdminManager(){
        return "/admin/admin/list";
    }


    /**
     * 获取管理员列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Page list(@RequestParam Map<String,Object> params){

        PageQuery query = new PageQuery(params);
        List<Admin> data = adminService .queryList(query);
        int total = adminService.queryTotal(query);
        Page page = new Page(data,total);
        return page;
    }


    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody Admin admin){

        R r = new R();

        Admin a = adminService.selectByName(admin.getUsername());
        if(a!=null){
            r.setCode(500);
            r.setMessage("用户名已存在！");
            return r;
        }
        Date date = new Date();
        String salt = StringUtils.getSalt();
        admin.setCreateTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        admin.setModifyTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        //正常
        admin.setSalt(salt);
        admin.setPassword(MD5.generatePasswordMD5(admin.getPassword(),salt));
        adminService.save(admin);
        r.setCode(200);
        r.setMessage("用户添加成功！");
        return r;

    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody  Admin admin){

        R r = new R();
        Admin a = adminService.selectById(admin.getId());
        a.setPassword(MD5.generatePasswordMD5(admin.getPassword(),a.getSalt()));
        adminService.update(a);
        r.setCode(200);
        r.setMessage("修改成功！");
        return r;
    }


    /**
     * 获取管理员详情
     * @param admin
     * @return
     */
    @RequestMapping("/getById")
    @ResponseBody
    public R getById(@RequestBody Admin admin){

        Admin a = adminService.selectById(admin.getId());
        R r = new R();
        r.setCode(200);
        r.setData(a);
        return r;
    }

    /**
     * 删除用户
     * @param admin
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public R remove(@RequestBody Admin admin){

        adminService.delete(admin.getId());
        R r = new R();
        r.setMessage("管理员删除成功！");
        r.setCode(200);
        return r;
    }


    /**
     * 密码重置
     * @param admin
     * @return
     */
    @RequestMapping("/reset")
    @ResponseBody
    public R reset(@RequestBody Admin admin){

        Admin a = adminService.selectById(admin.getId());
        a.setPassword(MD5.generatePasswordMD5("123456",a.getSalt()));
        adminService.update(a);
        R r = new R();
        r.setMessage("管理员密码重置成功！");
        r.setCode(200);
        return r;
    }




}
