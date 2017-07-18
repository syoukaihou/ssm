package com.snsprj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.snsprj.common.ServerResponse;
import com.snsprj.dao.UserMapper;
import com.snsprj.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by skh on 2017/7/3.
 */
@Controller
public class TestController {

    /// TODO test ===================================================

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;


    @RequestMapping(value = {"/test/user","test/get/user"})
    @ResponseBody
    public ServerResponse<User> getUser(){

        User user = userMapper.selectByPrimaryKey(1);

        ObjectMapper mapper = new ObjectMapper();

        return ServerResponse.createBySuccess(user);
    }


    @RequestMapping("/test/userdetial")
    @ResponseBody
    public ServerResponse<User> getUserDetial(){

        User user = userMapper.selectDetailByPrimaryKey(1);


        return ServerResponse.createBySuccess(user);
    }

    public ServerResponse<User> testPage(){

        PageHelper.startPage(1,10);

        return ServerResponse.createBySuccess();
    }



}
