package com.snsprj.controller;

import java.util.HashMap;
import java.util.Map;

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
    public void testSendMail(){
    	
    	String mail = "783520542@qq.com";
    	String[] receiver = {mail};
    	String subject = "重置密码";

        Map<String, String> map = new HashMap<>();
        map.put("username", mail);
        map.put("url", "http://www.baidu.com");
        map.put("admin", "admin@quarkdata.com");
        
        String templateName = "userActive.ftl";
        
        String htmlContent = iMailService.getMailText(map, templateName);

    	iMailService.htmlMail(receiver, subject, htmlContent);
    }

    @RequestMapping(value = "log")
    @ResponseBody
    public String testLog() {
        logger.info("测试info log");
        logger.error("测试error log");

        return "OK";
    }


    
    
    
    
   

}
