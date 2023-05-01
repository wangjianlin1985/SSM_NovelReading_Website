package com.xnnovel.xy.controller;

import com.xnnovel.xy.base.config.Global;
import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.entity.*;
import com.xnnovel.xy.utils.MD5;
import com.xnnovel.xy.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @RequestMapping("/index.html")
    public String index(ModelMap map){
        Map params = new HashMap();
        //本站排行 -- 根据点击量
        params.put("limit",5);
        List<Novel> novelListOrderByClick = novelService.queryOrderByClick(params);
        map.put("novelListOrderByClick",novelListOrderByClick);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);
        return "/user/profile";
    }

    @RequestMapping("/profile.html")
    public String profile(ModelMap map){

        Map params = new HashMap();
        //本站排行 -- 根据点击量
        params.put("limit",5);
        List<Novel> novelListOrderByClick = novelService.queryOrderByClick(params);
        map.put("novelListOrderByClick",novelListOrderByClick);

        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);

        return "/user/profile";
    }

    @RequestMapping("/password.html")
    public String password(ModelMap map){

        Map params = new HashMap();
        //本站排行 -- 根据点击量
        params.put("limit",5);
        List<Novel> novelListOrderByClick = novelService.queryOrderByClick(params);
        map.put("novelListOrderByClick",novelListOrderByClick);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);

        return "/user/password";
    }

    @RequestMapping("/shelf.html")
    public String shelf(HttpSession session, ModelMap map){

        User user = (User) session.getAttribute("user");
        Map params = new HashMap();
        params.put("userId",user.getId());
        List<ShelfVo> shelfList = shelfService.queryList(params);
        if(shelfList!=null&&shelfList.size()>0){
            for(ShelfVo shelfVo : shelfList){
                NovelVo novelVo = shelfVo.getNovel();
                ProgressVo progress = progressService.selectByNovel(novelVo.getId());
                if(progress != null){
                    shelfVo.setProgress(progress);
                }
            }
        }
        map.put("shelfList",shelfList);
        //本站排行 -- 根据点击量
        params.put("limit",5);
        List<Novel> novelListOrderByClick = novelService.queryOrderByClick(params);
        map.put("novelListOrderByClick",novelListOrderByClick);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);



        return "/user/shelf";
    }


    @RequestMapping("/favors.html")
    public String favors(HttpSession session,ModelMap map){
        User user = (User) session.getAttribute("user");
        Map params = new HashMap();
        params.put("userId",user.getId());
        List<FavorsVo> favorsList = favorsService.queryList(params);
        map.put("favorsList",favorsList);

        //本站排行 -- 根据点击量
        params.put("limit",5);
        List<Novel> novelListOrderByClick = novelService.queryOrderByClick(params);
        map.put("novelListOrderByClick",novelListOrderByClick);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);
        return "/user/favors";
    }

    @RequestMapping("/recoms.html")
    public String recoms(HttpSession session,ModelMap map){

        User user = (User) session.getAttribute("user");
        Map params = new HashMap();
        params.put("userId",user.getId());
        List<RecomsVo> recomsList = recomsService.queryList(params);
        map.put("recomsList",recomsList);

        //本站排行 -- 根据点击量
        params.put("limit",5);
        List<Novel> novelListOrderByClick = novelService.queryOrderByClick(params);
        map.put("novelListOrderByClick",novelListOrderByClick);
        //公告
        List<Notice> noticeList = noticeService.queryList(params);
        map.put("noticeList",noticeList);

        //热门推荐 -- 根据推荐量排行
        params.put("limit",10);
        List<Novel> novelListOrderByRecoms = novelService.queryOrderByRecoms(params);
        map.put("novelListOrderByRecoms",novelListOrderByRecoms);

        //收藏排行
        List<Novel> novelListOrderByFavors = novelService.queryOrderByFavors(params);
        map.put("novelListOrderByFavors",novelListOrderByFavors);

        return "/user/recoms";
    }

    @RequestMapping("/update")
    @ResponseBody
    public R update(HttpServletRequest request){

        R r = new R();
        User u = (User) request.getSession().getAttribute("user");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
        MultipartFile multipartFile = multipartRequest.getFile("file");
        // 接收其他表单参数
        String nickName = multipartRequest.getParameter("nickName");
        String email = multipartRequest.getParameter("email");
        String bannerPath = Global.getConfig("avatar.path");
        if(multipartFile.getSize()>0){
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
                u.setAvatar("/uploadImage/avatar/"+newFileName);
            } catch (IOException ex) {
                r.setCode(500);
                r.setMessage("修改失败！");
                return r;
            }
        }
        u.setNickName(nickName);
        u.setEmail(email);
        userService.update(u);
        request.getSession().setAttribute("user",u);
        r.setCode(200);
        r.setMessage("修改成功！");
        return r;
    }


    @RequestMapping("updatePwd")
    @ResponseBody
    public R updatePwd(@RequestBody Map<String,String> map,HttpSession session){

        R r = new R();
        String orginPwd = map.get("orginPwd");
        String newPwd = map.get("newPwd");
        User user = (User) session.getAttribute("user");
        String encryPwd = MD5.generatePasswordMD5(orginPwd,user.getSalt());
        if(!encryPwd.equals(user.getPassword())){
            r.setCode(500);
            r.setMessage("原始密码错误！");
            return r;
        }
        user.setPassword(MD5.generatePasswordMD5(newPwd,user.getSalt()));
        userService.update(user);
        session.removeAttribute("user");
        r.setCode(200);
        r.setMessage("密码修改成功！");
        return r;
    }
}
