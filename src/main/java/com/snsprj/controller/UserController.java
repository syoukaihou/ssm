package com.snsprj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snsprj.common.PagePath;
import com.snsprj.common.ServerResponse;
import com.snsprj.dto.User;
import com.snsprj.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by skh on 2017/6/6.
 */
@Controller
public class UserController {

    @Autowired
    private IUserAuthService iUserAuthService;

    /**
     * post login
     * @return json
     */
    @RequestMapping(value = "auth/login" ,method={RequestMethod.POST})
    @ResponseBody
    public ServerResponse<User> login(){


        return ServerResponse.createBySuccess();
    }


    /**
     *
     * @return login page
     */
    @RequestMapping(value="auth/login",method = {RequestMethod.GET})
    public String Index(){

        return PagePath.userLogin;
    }





}
