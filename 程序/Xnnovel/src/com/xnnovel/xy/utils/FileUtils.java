package com.xnnovel.xy.utils;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * 文件相关工具类
 * @author：Mr.Yang
 * @date：2018/9/13 20:06
 */
public class FileUtils {

    private static String[] allowFiles = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

    /**
     * 图片上传返回信息
     */
    public static HashMap<String, String> errorInfo = new HashMap<String,String>();


    static {

        try {
            //默认成功
            errorInfo.put("SUCCESS", "SUCCESS");
            errorInfo.put("NOFILE", URLEncoder.encode("未包含文件上传域","UTF-8"));
            errorInfo.put("TYPE", URLEncoder.encode("不允许的文件格式","UTF-8"));
            errorInfo.put("SIZE", URLEncoder.encode("文件大小超出限制，最大支持2Mb","UTF-8"));
            errorInfo.put("ENTYPE", URLEncoder.encode("请求类型ENTYPE错误","UTF-8"));
            errorInfo.put("REQUEST", URLEncoder.encode("上传请求异常","UTF-8"));
            errorInfo.put("IO", URLEncoder.encode("IO异常","UTF-8"));
            errorInfo.put("DIR", URLEncoder.encode("目录创建失败","UTF-8"));
            errorInfo.put("UNKNOWN", URLEncoder.encode("未知错误","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取文件后缀,例如：[.jpg]
     * @param fileName 文件名
     * @return
     */
    public static String getSuffix(String fileName){

        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }


    /**
     * 判断文件目录是否存在，不存在则创建
     * @param file 必须为具体的文件路径,例如：[D:\tmp\test.txt]，而不是文件目录
     */
    public static void checkDirAndCreate(File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }


    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    public static boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }



}
