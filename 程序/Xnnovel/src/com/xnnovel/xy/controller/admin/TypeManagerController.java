package com.xnnovel.xy.controller.admin;

import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.entity.Type;
import com.xnnovel.xy.utils.DateUtils;
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
@RequestMapping("/admin/type")
public class TypeManagerController extends BaseController {

    //跳转到小说类型管理
    @RequestMapping("")
    public String toTypeManager(){
        return "admin/type/list";
    }

    /**
     * 获取小说类型并分页
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Page list(@RequestParam Map<String,Object> params){

        PageQuery query = new PageQuery(params);
        List<Type> data = typeService.queryList(query);
        int total = typeService.queryTotal(query);
        Page page = new Page(data,total);
        return page;
    }



    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody Type type){

        Date date = new Date();
        type.setCreateTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        type.setModifyTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        typeService.save(type);
        R r = new R();
        r.setCode(200);
        r.setMessage("保存成功！");
        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody Type type){

        Type t = typeService.selectById(type.getId());
        t.setTypeName(type.getTypeName());
        Date date = new Date();
        t.setCreateTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        t.setModifyTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        typeService.update(t);
        R r = new R();
        r.setCode(200);
        r.setMessage("修改成功！");
        return r;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Type type){

        typeService.delete(type.getId());
        R r = new R();
        r.setCode(200);
        r.setMessage("删除成功！");
        return r;

    }


}
