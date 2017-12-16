package com.snsprj.controller;

import javax.servlet.http.HttpServletRequest;

import com.snsprj.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;

/**
 * Created by skh on 2017/7/3.
 */
@Controller
@RequestMapping("/test")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping(value = "log")
    @ResponseBody
    public String testLog(HttpServletRequest request) {

        String jsonStr = request.getParameter("jsonData");

        System.out.println(jsonStr);

        logger.info("测试info log");
        logger.error("测试error log");

        return "OK";
    }


    /**
     * 测试文件上传
     * @param request HttpServletRequest
     * @return ServerResponse
     */
    public ServerResponse<Object> testFileUpload(HttpServletRequest request){

        MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;

        Iterator<String> iterable = multiRequest.getFileNames();

        while (iterable.hasNext()){
            MultipartFile file =  multiRequest.getFile(iterable.next());

            if (file != null){
                logger.info(file.getName());
            }
        }

        return ServerResponse.createBySuccess();
    }

}
