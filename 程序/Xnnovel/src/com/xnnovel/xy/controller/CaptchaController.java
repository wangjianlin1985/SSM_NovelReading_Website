package com.xnnovel.xy.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author：Mr.Yang
 * @date：2018/9/17 14:39
 */
@Controller
public class CaptchaController {

    private static Logger logger = LoggerFactory.getLogger(CaptchaController.class);
    @Autowired
    private Producer producer;

    /**
     * 使用谷歌的kaptcha插件生成验证码：可配置，多样化
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response, HttpSession session)throws ServletException, IOException {

        //指定请求和相应遵循的缓存机制：no-cache指示请求或响应消息不能缓存；no-store用于防止重要的信息被无意的发布。在请求消息中发送将使得请求和响应消息都不使用缓存。
        response.setHeader("Cache-Control", "no-store, no-cache");
        //设置响应内容的MIMI类型为图片
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        logger.info("verifyCode:{}",text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        //图片写到浏览器
        ImageIO.write(image, "jpg", out);
    }

}
