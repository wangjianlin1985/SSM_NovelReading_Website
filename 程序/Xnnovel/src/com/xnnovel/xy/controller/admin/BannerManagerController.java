package com.xnnovel.xy.controller.admin;

import com.xnnovel.xy.base.config.Global;
import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.Banner;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.entity.User;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 网站首页banner轮播管理
 */
@Controller
@RequestMapping("/admin/banner")
public class BannerManagerController extends BaseController {

    @RequestMapping("")
    public String toBannerManager(){
        return "/admin/banner/list";
    }


    /**
     * 获取轮播列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Page list(@RequestParam Map<String,Object> params){

        PageQuery query = new PageQuery(params);
        List<Banner> data = bannerService.queryList(query);
        int total = bannerService.queryTotal(query);
        Page page = new Page(data,total);
        return page;
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,ModelMap map){

        String id = request.getParameter("id");

        if(!StringUtils.isEmpty(id)){
            Banner banner = bannerService.selectById(Integer.valueOf(id));
            map.put("banner",banner);
        }
        return "/admin/banner/edit";

    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public R save(HttpServletRequest request){
        R r =  new R();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
        MultipartFile multipartFile = multipartRequest.getFile("file");
        // 接收其他表单参数
        String title = multipartRequest.getParameter("title");
        String link = multipartRequest.getParameter("link");
        String bannerPath = Global.getConfig("banner.path");
        String fileSuffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileSuffix;
        String newFilePath = bannerPath  + newFileName;
        File dest = new File(bannerPath);
        if(!dest.exists()){
            dest.mkdirs();
        }
        // 创建文件实例
        File uploadFile = new File(newFilePath);
        // 利于spring中的FileCopyUtils.copy()将文件复制
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), uploadFile);
        } catch (IOException e) {
            r.setCode(500);
            r.setMessage("保存失败！");
            return r;
        }
        Banner banner = new Banner();
        banner.setTitle(title);
        banner.setLink(link);
        banner.setCover("/uploadImage/banner/"+newFileName);
        banner.setStatus(1);
        String date = DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN);
        banner.setCreateTime(date);
        banner.setModifyTime(date);
        bannerService.save(banner);
        r.setCode(200);
        r.setMessage("保存成功！");
        return r;
    }



    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public R update(HttpServletRequest request){
        R r =  new R();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
        MultipartFile multipartFile = multipartRequest.getFile("file");
        // 接收其他表单参数
        Integer id = Integer.valueOf(multipartRequest.getParameter("id"));
        String title = multipartRequest.getParameter("title");
        String link = multipartRequest.getParameter("link");
        Banner banner = bannerService.selectById(id);
        if(multipartFile.getSize()>0){
            String bannerPath = Global.getConfig("banner.path");
            String fileSuffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileSuffix;
            String newFilePath = bannerPath  + newFileName;
            File dest = new File(bannerPath);
            if(!dest.exists()){
                dest.mkdirs();
            }
            // 创建文件实例
            File uploadFile = new File(newFilePath);
            // 利于spring中的FileCopyUtils.copy()将文件复制
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), uploadFile);
            } catch (IOException ex) {
                r.setCode(500);
                r.setMessage("修改失败！");
                return r;
            }
            banner.setCover("/uploadImage/banner/"+newFileName);
        }
        banner.setTitle(title);
        banner.setLink(link);
        String date = DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN);
        banner.setModifyTime(date);
        bannerService.update(banner);
        r.setCode(200);
        r.setMessage("修改成功！");
        return r;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Banner banner){

        bannerService.delete(banner.getId());
        R r = new R();
        r.setCode(200);
        r.setMessage("轮播删除成功");
        return r;
    }



    /**
     * 轮播图开启关闭
     * @param banner
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public R audit(@RequestBody Banner banner){

        Banner ban = bannerService.selectById(banner.getId());
        ban.setStatus(banner.getStatus());
        bannerService.update(ban);
        R r = new R();
        r.setCode(200);
        r.setMessage("操作成功！");
        return r;
    }
}
