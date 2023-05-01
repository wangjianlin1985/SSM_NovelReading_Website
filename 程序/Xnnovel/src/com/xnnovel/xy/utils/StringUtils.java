package com.xnnovel.xy.utils;

import java.util.Random;

public class StringUtils {

    private static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0','1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 统计一段字符中字数（模仿office-word计数）
     * 规则：一个中文算一个字，其他的所有非中文统计成一个字（所有非中文算一个字）
     * @param context
     * @return
     */
    public static int getTotalWordsCount(String context) {

        int words_count = 0;
        //中文单词
        String cn_words = context.replaceAll("[^(\\u4e00-\\u9fa5，。《》？；’‘：“”【】、）（……￥！·)]", "");
        int cn_words_count = cn_words.length();
        //非中文单词
        String non_cn_words = context.replaceAll("[^(a-zA-Z0-9`\\-=\';.,/~!@#$%^&*()_+|}{\":><?\\[\\])]", " ");
        int non_cn_words_count = 0;
        String[] ss = non_cn_words.split(" ");
        for (String s : ss) {
            if (s.trim().length() != 0) non_cn_words_count++;
        }
        //中文和非中文单词合计
        words_count = cn_words_count + non_cn_words_count;
        return words_count;
    }


    /**
     * 生成6位密码加密盐
     * @return
     */
    public static String getSalt() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int r = 0;
        for(int i=0;i<6;i++){
            r = random.nextInt(36);
            sb.append(chars[r]);
        }
        return sb.toString();

    }


    public static void main(String[] args) {
        System.out.println(getTotalWordsCount("哈哈哈哈11111"));
        System.out.println("哈哈哈哈11111".length());
    }
}
