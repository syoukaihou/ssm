package com.snsprj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snsprj.service.UserAuthService;
import com.snsprj.utils.ImageCaptcha;

@Controller
public class HelloController {

    private static Logger logger = LoggerFactory
            .getLogger(HelloController.class);

    @RequestMapping(value = "/mvc/{id}", method = { RequestMethod.GET,
            RequestMethod.POST })
    public String hello(@RequestParam("name") String name,
            @PathVariable("id") Integer id) {

        System.out.println("" + System.getProperty("webapp.root"));
        logger.info("{},{}", name, id);
        return "jsps/hello";
    }

    //
    @RequestMapping(value = "/hi", method = { RequestMethod.POST })
    public String hi(HttpServletRequest request) {

        String name = request.getParameter("name");

        Integer age = Integer.valueOf(request.getParameter("age"));

        logger.debug("{},{}", name, age);
        return "";
    }

    @Autowired
    UserAuthService userAuthService;

    // @RequestMapping(value="/mybatis",produces = "text/json;charset=UTF-8")
    @RequestMapping(value = "/mybatis")
    @ResponseBody
    public String testMybatis() {

        boolean flag = userAuthService.login("123", "123");

        return "登录结果：" + flag;
    }

    @RequestMapping(value = "getVerify")
    public void getVerify(HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        // private int width = 95;// 图片宽
        // private int height = 25;// 图片高
        // private int lineSize = 40;// 干扰线数量
        // private int stringNum = 4;// 随机产生字符数量
        ImageCaptcha imageCaptcha = new ImageCaptcha(95, 25, 4, 30);
        try {
            imageCaptcha.getRandcode(request, response);// 输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login", method = { RequestMethod.GET })
    public String getLogin(HttpServletRequest request) {

        return "jsps/login";
    }

}
