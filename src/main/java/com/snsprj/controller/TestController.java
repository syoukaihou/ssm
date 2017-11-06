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
    public void testSendMail(){
    	
    	String[] receiver = {"783520542@qq.com"};
    	String subject = "重置密码";
    	 
    	// 正文  
        StringBuilder builder = new StringBuilder();  
        builder.append("<html><head>");  
        builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");  
        builder.append("</head><body>");  
        builder.append("您好，张三：<br />");  
        builder.append("\t系统已为您重置了RUI密码，账户信息如下：<br />");  
        builder.append("用户账户：zhangsan<br />用户密码：123456<br />您可以点击以下链接登录RUI：");  
        builder.append("<a href=\"");  
        builder.append("www.baidu.com");  
        builder.append("\">");  
        builder.append("www.baidu.com");  
        builder.append("</a>");  
        builder.append("</body></html>");  
        String htmlContent = builder.toString();  

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
