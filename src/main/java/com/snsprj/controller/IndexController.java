package com.snsprj.controller;

import com.snsprj.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by skh on 2017/6/6.
 */
@Controller
public class IndexController {

    @Autowired
    IUserAuthService iUserAuthService;


    @RequestMapping(value = "/index")
    public String login(){

        Boolean result = iUserAuthService.login("123","123456");

        return "" + result;
    }
}
