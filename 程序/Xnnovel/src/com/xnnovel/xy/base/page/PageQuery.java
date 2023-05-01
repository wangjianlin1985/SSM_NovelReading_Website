package com.xnnovel.xy.base.page;

import com.xnnovel.xy.base.config.Global;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询参数转换
 * 使用Map传参，调用mybatis查询
 *
 * @author：Mr.Yang
 * @date：2018/9/15 13:27
 */
public class PageQuery extends HashMap {

    //开始条数
    private int start = 0;
    //每页几条
    private int limit = 0;
    //当前第几页
    private int pageNo = 1;
    //每页几条
    private int pageSize = 10;
    private String sort;
    //排序：asc or desc
    private String order;



    {
        pageSize = Global.propertiesLoader.getInteger("page.size");
    }

    /**
     * 只查第一页
     */
    public PageQuery(){
        this.limit = this.pageSize;
        this.put("start", this.start);
        this.put("limit", this.limit);
    }

    /**
     * <p>
     *  针对前端自己封装的简答分页，此时前端只会给后台传递一个pageNo参数，
     *  至于pageSize(每页几条)已经写入配置文件，获取配置即可
     * 根据输入的页数，自动转换start,limit
     * 便于mysql数据库查询
     * </p>
     * @param pageNo
     */
    public PageQuery(Integer pageNo) {

        if(StringUtils.isEmpty(pageNo)){
            pageNo = 1;
        }
        this.pageNo = pageNo;
        this.limit = this.pageSize;
        /**
         * mysql的start从0开始
         */
        this.start = (pageNo - 1) * this.pageSize;
        this.put("start", this.start);
        this.put("limit", this.limit);
    }


    public PageQuery(Map<String,Object> params) {

        this.putAll(params);
        /**
         * 这两个参数必须转化为int型，mybatis才能查到结果，原因未知，日了狗
         */

        this.put("start",Integer.parseInt(params.get("start").toString()));
        this.put("limit",Integer.parseInt(params.get("limit").toString()));
    }





    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }


}
