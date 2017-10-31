package com.snsprj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snsprj.service.IMailService;

/**
 * Created by skh on 2017/7/3.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private IMailService iMailService;

    @RequestMapping(value = "mail")
    @ResponseBody
    public void testSendMail() {

        String[] receiver = { "syoukaihou@gmail.com" };
        String subject = "重置密码";
        String html = "<p>点击链接重置密码！</p>";

        iMailService.htmlMail(receiver, subject, html);
    }

    @RequestMapping(value = "log")
    @ResponseBody
    public String testLog() {
        logger.info("测试info log");
        logger.error("测试error log");

        return "OK";
    }

}
