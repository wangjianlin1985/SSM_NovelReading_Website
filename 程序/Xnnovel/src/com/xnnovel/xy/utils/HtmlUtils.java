package com.xnnovel.xy.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author：Mr.Yang
 * @date：2018/11/6 19:14
 */
public class HtmlUtils {


    /**
     * 获取html中所有图片地址
     *
     * @param html
     * @return
     */
    public static String getImgURL(String html) {
        List<String> srcList = new ArrayList<String>(); //用来存储获取到的图片地址
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");//匹配字符串中的img标签
        Matcher matcher = p.matcher(html);
        boolean hasPic = matcher.find();
        while (hasPic) //如果含有图片，那么持续进行查找，直到匹配不到
        {
            String group = matcher.group(2);//获取第二个分组的内容，也就是 (.*?)匹配到的
            Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");//匹配图片的地址
            Matcher matcher2 = srcText.matcher(group);
            if (matcher2.find()) {
                return matcher2.group(3);
            }
        }
        return null;
    }


    //获取html中的纯文本信息（不包含）
    public static String html2Text(String inputString){
        String htmlStr = inputString; //含html标签的字符串
        String textStr ="";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try{
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); //过滤script标签
            p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); //过滤style标签
            p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); //过滤html标签
            //去除&nbsp;等空格
            textStr = htmlStr.replaceAll("&nbsp;","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return textStr;//返回文本字符串
    }
}
