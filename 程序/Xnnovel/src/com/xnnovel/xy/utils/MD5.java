/*
+--------------------------------------------------------------------------
|   mtons [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.xnnovel.xy.utils;


import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5摘要
 */
public class MD5 {
	
	/**
	 * 对字符串进行Md5加密
	 * 
	 * @param input 原文
	 * @return md5后的密文
	 */
	public static String md5(String input) {
		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(input.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 对字符串进行Md5加密
	 * 
	 * @param input 原文
	 * @param salt 随机数
	 * @return string
	 */
	public static String generatePasswordMD5(String input, String salt) {
		if(StringUtils.isEmpty(salt)) {
			salt = "";
		}
		return md5(salt + md5(input));
	}

}
