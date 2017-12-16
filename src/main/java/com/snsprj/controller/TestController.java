package com.snsprj.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by skh on 2017/7/3.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "log")
    @ResponseBody
    public String testLog(HttpServletRequest request) {

        String jsonStr = request.getParameter("jsonData");

        System.out.println(jsonStr);

        logger.info("测试info log");
        logger.error("测试error log");

        return "OK";
    }


}
