package com.snsprj.controller;

import com.snsprj.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by skh on 2017/6/6.
 */
public class IndexController {

    @Autowired
    IUserAuthService userAuthService;


    public String login(){

        Boolean result = userAuthService.login("123","123456");

        return "" + result;
    }
}
