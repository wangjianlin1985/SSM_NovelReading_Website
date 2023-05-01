package com.xnnovel.xy.utils;

import java.util.UUID;

/**
 * @author：Mr.Yang
 * @date：2018/9/13 20:44
 */
public class UUIDUtils {

    /**
     * 获取带'-'的原始的UUID
     * @return
     */
    public static String getOrginalUUID(){

        return UUID.randomUUID().toString();

    }

    /**
     * 获取UUID，去除'-'
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
