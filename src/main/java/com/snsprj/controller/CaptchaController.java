package com.snsprj.controller;

import com.google.code.kaptcha.Producer;
import com.snsprj.common.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Created by skh on 2017/6/21.
 */
@Controller
@RequestMapping(value={"/captcha"})
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    @RequestMapping("img")
    public void getImgCaptcha(HttpServletRequest request, HttpServletResponse response){

        HttpSession httpSession = request.getSession();

        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");

        // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);


        String capText = captchaProducer.createText();

        httpSession.setAttribute(Const.imageCaptchaSessionKey,capText);

        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
