package com.xnnovel.xy.base.page;


import com.xnnovel.xy.base.config.Global;

import java.util.Collections;
import java.util.List;

/**
 * @author：Mr.Yang
 * @date：2018/9/14 15:43
 */
public class Page<T> {

    /**
     * 当前页数据列表
     */
    private List<T> rows;

    /**
     * 页大小（每页数据个数）
     */
    private int pageSize;

    /**
     * 当前页号
     */
    private int pageNo;

    /**
     * 数据总个数
     */
    private int total = 0;

    private boolean isLast;

    private boolean isFirst;

    private int totalPage = 0;


    public int getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }


    public void setTotal(int total) {
        this.total = total;
    }

    public boolean getIsLast() {
        return isLast;
    }


    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }


    public boolean getIsFirst() {
        return isFirst;
    }


    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }


    /**
     * 针对网站前端自定义分页，封装的响应参数
     * 根据当前页号、页大小（每页数据个数）、当前页数据列表、数据总个数构造分页数据对象的实例。
     * @param pageNo     当前页号
     * @param rows   当前页数据列表
     * @param total 数据总个数
     */
    public Page(int pageNo, List<T> rows, int total) {
        this.pageNo = pageNo;
        this.pageSize = Global.propertiesLoader.getInteger("page.size");
        this.rows = rows;
        this.total = total;
        this.isLast = isLastPage();
        this.isFirst = isFirstPage();
        this.totalPage = getTotalPageNum();
    }

    /**
     * bootstrap-table分页响应参数封装
     * @param rows
     * @param total
     */
    public Page(List<T> rows, int total) {

        //参数名称不允许修改
        this.rows = rows;
        this.total = total;
    }



    public int getTotalPageNum() {
        if (pageSize != 0) {
            if (pageSize - total > 0) {
                return 1;
            }
            if (total % pageSize == 0) {
                return total / pageSize;
            } else {
                return total / pageSize + 1;
            }
        } else {
            return 0;
        }

    }

    /**
     * 定义一空页
     *
     * @see #emptyPage()
     */
    @SuppressWarnings("rawtypes")
    public static final Page EMPTY_PAGE = new Page(
            0, Collections.emptyList(), 0);

    /**
     * 获取一空页
     */
    public static <E> Page<E> emptyPage() {
        return (Page<E>) EMPTY_PAGE;
    }


    public boolean isFirstPage() {
        return getPageNo() == 1;
    }


    public boolean isLastPage() {
        return getPageNo() >= getLastPageNo();
    }


    public boolean hasNextPage() {
        return rows == null ? false : rows.size() > getPageSize();
    }


    public boolean hasPreviousPage() {
        return getPageNo() > 1;
    }


    public int getLastPageNo() {
        double totalResults = new Integer(getTotal()).doubleValue();
        return (totalResults % getPageSize() == 0) ? new Double(
                Math.floor(totalResults / getPageSize())).intValue()
                : (new Double(Math.floor(totalResults / getPageSize()))
                .intValue() + 1);
    }


    public List<T> getThisPagerows() {
        return hasNextPage() ? rows.subList(0, getPageSize()) : rows;
    }


    public int getTotal() {
        return total;
    }


    public int getThisPageFirstElementNumber() {
        return getPageNo() * getPageSize() + 1;
    }


    public int getThisPageLastElementNumber() {
        int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
        return getTotal() < fullPage ? getTotal() : fullPage;
    }

    public int getNextPageNo() {
        return getPageNo() + 1;
    }

    public int getPreviousPageNo() {
        return getPageNo() - 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    /**
     * 根据页大小（每页数据个数）获取给定页号的第一条数据在总数据中的位置（从1开始）
     *
     * @param pageNo   给定的页号
     * @param pageSize 页大小（每页数据个数）
     * @return 给定页号的第一条数据在总数据中的位置（从1开始）
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize + 1;
        if (startIndex < 1)
            startIndex = 1;
        return startIndex;
    }
}
