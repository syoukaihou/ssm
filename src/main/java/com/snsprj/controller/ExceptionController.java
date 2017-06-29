package com.snsprj.controller;

import com.snsprj.common.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by skh on 2017/6/29.
 */
@Controller
public class ExceptionController {

    @RequestMapping("404")
    public String error404(){

        return PagePath.error404;
    }

    @RequestMapping("500")
    public String error500(){

        return PagePath.error500;
    }
}
