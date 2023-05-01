package com.xnnovel.xy.base.config;


import com.xnnovel.xy.base.consts.Consts;
import com.xnnovel.xy.base.utils.PropertiesLoader;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 系统全局变量配置类
 * @author：Mr.Yang
 * @date：2018/9/13 19:42
 */
public class Global {

    /**
     * 保存全局属性值
     * 并发容器：线程安全
     */
    private static ConcurrentMap<String,String> map = new ConcurrentHashMap<String,String>();

    /**
     * 属性文件加载对象
     */
    public static PropertiesLoader propertiesLoader = new PropertiesLoader(Consts.CONFIG);


    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = propertiesLoader.getProperty(key);
            map.put(key, value);
        }
        return value;
    }
}
