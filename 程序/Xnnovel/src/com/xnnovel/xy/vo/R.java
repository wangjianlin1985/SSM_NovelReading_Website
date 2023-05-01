package com.xnnovel.xy.vo;

import java.io.Serializable;

/**
 * 异步请求数据封装
 * @author：Mr.Yang
 * @date：2018/9/14 14:43
 */
public class R implements Serializable{

    private static final long serialVersionUID = 1L;

    //响应码
    private int code;
    //消息
    private String message;
    //数据
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
