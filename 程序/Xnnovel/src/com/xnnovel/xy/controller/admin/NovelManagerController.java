package com.xnnovel.xy.controller.admin;

import com.xnnovel.xy.base.config.Global;
import com.xnnovel.xy.base.consts.Consts;
import com.xnnovel.xy.base.controller.BaseController;
import com.xnnovel.xy.base.page.Page;
import com.xnnovel.xy.base.page.PageQuery;
import com.xnnovel.xy.entity.Chapter;
import com.xnnovel.xy.entity.Novel;
import com.xnnovel.xy.entity.Type;
import com.xnnovel.xy.utils.DateUtils;
import com.xnnovel.xy.utils.HtmlUtils;
import com.xnnovel.xy.utils.HttpClientUtils;
import com.xnnovel.xy.vo.NovelVo;
import com.xnnovel.xy.vo.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author：Mr.Yang
 * @date：2018/10/8 11:03
 */
@Controller
@RequestMapping("/admin/novel")
public class NovelManagerController extends BaseController {



    private Logger logger = LoggerFactory.getLogger(NovelManagerController.class);

    /**
     * 前往小说管理页面
     * @param map
     * @return
     */
    @RequestMapping("")
    public String toNovelManager(ModelMap map){

        List<Type> typeList = typeService.queryList(new HashMap());
        map.put("typeList",typeList);
        return "admin/novel/list";
    }


    /**
     * 获取小说列表并分页
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Page list(@RequestParam Map<String,Object> params){

        PageQuery query = new PageQuery(params);
        List<NovelVo> data = novelService.queryList(query);
        int total = novelService.queryTotal(query);
        Page page = new Page(data,total);
        return page;
    }

    /**
     * 通过小说添加或者编辑页面
     * @return
     */
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,ModelMap model){

        //小说id，如果不为空，说明是添加，否则编辑
        String id = request.getParameter("id");
        //获取所有小说分类
        List<Type> typeList = typeService.queryList(new HashMap());
        model.put("typeList",typeList);
        if(!StringUtils.isEmpty(id)){
            Novel novel = novelService.selectById(Integer.valueOf(id));
            model.put("novel",novel);
        }
        return "admin/novel/edit";
    }


    /**
     * 通过小说添加或者编辑页面
     * @return
     */
    @RequestMapping("/view")
    public String view(HttpServletRequest request,ModelMap model){

        //小说id，如果不为空，说明是添加，否则编辑
        String id = request.getParameter("id");
        //获取所有小说分类
        List<Type> typeList = typeService.queryList(new HashMap());
        model.put("typeList",typeList);
        Novel novel = novelService.selectById(Integer.valueOf(id));
        model.put("novel",novel);
        return "admin/novel/view";
    }


    @RequestMapping("/save")
    @ResponseBody
    public R save(HttpServletRequest request){
        R r =  new R();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
        MultipartFile multipartFile = multipartRequest.getFile("file");
        // 接收其他表单参数
        //小说名
        String title = multipartRequest.getParameter("title");
        //小说作者
        String author = multipartRequest.getParameter("author");
        //小说介绍html文本
        String summary = multipartRequest.getParameter("summary");
        //小说介绍纯文本
        String summaryText = multipartRequest.getParameter("summaryText");
        //小说类型
        Integer type = Integer.valueOf(multipartRequest.getParameter("type"));
        //数据源
        String source = multipartRequest.getParameter("source");
        //小说封面存放地址，路径配置在config.properties中
        String bannerPath = Global.getConfig("cover.path");
        //获取图片后缀【jpg,png】
        String fileSuffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        //生成新的文件名
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileSuffix;
        //存放地址
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
        Novel novel = new Novel();
        novel.setTitle(title);
        novel.setSummary(summary);
        novel.setSummaryText(summaryText);
        novel.setTypeId(type);
        novel.setSource(source);
        novel.setStatus(Consts.ENABLED);
        novel.setCoverUrl("/uploadImage/cover/"+newFileName);
        Date date = new Date();
        novel.setCreateTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        novel.setModifyTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
        novel.setAuthor(author);
        novel.setFavors(0);
        novel.setClicks(0);
        novel.setRecommends(0);
        //设置未完结
        novel.setIsCompleted(1);
        novel.setTotalWords(0);
        novel.setLatest(0);
        novel.setLatestSpider(0);
        novelService.save(novel);
        r.setCode(200);
        r.setMessage("保存成功！");
        return r;
    }

    @RequestMapping("/update")
    @ResponseBody
    public R update(HttpServletRequest request){
        R r =  new R();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
        MultipartFile multipartFile = multipartRequest.getFile("file");
        // 接收其他表单参数
        //小说id
        Integer id = Integer.valueOf(multipartRequest.getParameter("id"));
        //小说名
        String title = multipartRequest.getParameter("title");
        //小说作者
        String author = multipartRequest.getParameter("author");
        //小说介绍html文本
        String summary = multipartRequest.getParameter("summary");
        //小说介绍纯文本
        String summaryText = multipartRequest.getParameter("summaryText");
        //小说类型
        Integer type = Integer.valueOf(multipartRequest.getParameter("type"));
        //数据源
        String source = multipartRequest.getParameter("source");
        NovelVo  novel = novelService.selectById(id);
        //有上传的文件图片需要处理
        if(multipartFile.getSize()>0){
            //小说封面存放地址，路径配置在config.properties中
            String bannerPath = Global.getConfig("cover.path");
            //获取图片后缀【jpg,png】
            String fileSuffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            //生成新的文件名
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileSuffix;
            //存放地址
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
                r.setMessage("编辑失败！");
                return r;
            }
            novel.setCoverUrl("/uploadImage/cover/"+newFileName);
        }
        novel.setTitle(title);
        novel.setSummary(summary);
        novel.setSummaryText(summaryText);
        novel.setTypeId(type);
        novel.setSource(source);
        novel.setModifyTime(DateUtils.dateFormat(new Date(),DateUtils.DATE_TIME_PATTERN));
        novel.setAuthor(author);
        novelService.update(novel);
        r.setCode(200);
        r.setMessage("编辑成功！");
        return r;
    }


    @ResponseBody
    @RequestMapping("/remove")
    public R remove(@RequestBody Map<String,Integer[]>map){

        Integer[] ids = map.get("ids");
        novelService.deleteByBanch(ids);
        R r = new R();
        r.setCode(200);
        r.setMessage("删除成功！");
        return r;

    }

    /**
     * 切换小说状态，上下架
     * @param map
     * @return
     */
    @RequestMapping("/changeStatus")
    @ResponseBody
    public R changeStatus(@RequestBody Map<String,Object> map){

        Integer novelId = Integer.valueOf(map.get("novelId").toString());
        Integer status = Integer.parseInt(map.get("status").toString());
        novelService.changeStatus(novelId,status);
        R r = new R();
        r.setCode(200);
        if(status == Consts.ENABLED){
            r.setMessage("上架成功");
        }else {
            r.setMessage("下架成功");
        }
        return r;
    }


    @RequestMapping("/spider")
    @ResponseBody
    public R spider(HttpServletRequest request){

        R r = new R();
        Integer id = Integer.valueOf(request.getParameter("id"));
        NovelVo novel = novelService.selectById(id);
        String url = novel.getSource();
        if(StringUtils.isEmpty(url)){
            r.setCode(500);
            r.setMessage("数据源没有设置，无法进行爬取！");
            return r;
        }
        this.thread4Spider(novel);
        r.setCode(200);
        r.setMessage("服务器正在进行章节的爬取....");
        return r;
    }


    private void thread4Spider(final Novel novel){ 
        new Thread(){
            public void run(){
                logger.info("开始爬取小说【{}】下的所有章节...",novel.getTitle());
                String html = "";
                try {
                    html = HttpClientUtils.get(novel.getSource());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //讲爬取的网页信息转化为文档格式
                Document doc = Jsoup.parse(html);
                //获取所有td标签，也就是所有章节的url链接 
                Elements elements = doc.select("div[id='list'] dd a");
                //该小说总的章节数
                int chapters = elements.size();
                //记录爬取的最后一章
                int index = 0;
                for(int i=0;i<chapters;i++){
                    String href = "https://www.booktxt.net" + elements.get(i).attr("href");
                    String chapterTitle = elements.get(i).text();
                    //Integer cid = Integer.valueOf(href.substring(href.lastIndexOf("/")+1,href.lastIndexOf(".")));
                    //if(cid>novel.getLatestSpider()){ 
                    Chapter chapter = chapterService.queryByChapterURL(href);
                    if(chapter == null) {
                        logger.info("爬取章节===>{}",href);
                        chapter = new Chapter();
                        chapter.setTitle(chapterTitle);
                        chapter.setNovelId(novel.getId());
                        chapter.setChapterURL(href);
                        String chapterHtml;
                        try {
                            chapterHtml = HttpClientUtils.get(href);
                            Document chapDoc = Jsoup.parse(chapterHtml);
                            String content = chapDoc.select("div[id='content']").html();
                            chapter.setContent(content);
                            Date date = new Date();
                            chapter.setCreateTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
                            chapter.setModifyTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
                            chapter.setContentText(HtmlUtils.html2Text(content));
                            chapterService.save(chapter);
                        } catch (IOException e) {
                            /**
                             * 爬取过程中可能存在网络异常，导致爬取失败
                             * 此时，我们保存一条空记录，这样方便后面进行修改（或者针对这一章节，进行再次爬取）
                             * 同时也不会中断后续章节的爬取
                             */
                            chapter.setContent(null);
                            Date date = new Date();
                            chapter.setCreateTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
                            chapter.setModifyTime(DateUtils.dateFormat(date,DateUtils.DATE_TIME_PATTERN));
                            chapterService.save(chapter);
                        }
                        //if(i+1==chapters){
                        //    index = cid;
                        //}
                    }
                }
                Novel nov = novelService.selectById(novel.getId());
                //nov.setLatestSpider(index);
                novelService.update(nov);
                logger.info("小说【{}】所有章节爬取完成！",novel.getTitle());
            }
        }.start();
    }
}
