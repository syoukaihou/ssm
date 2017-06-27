package com.snsprj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.snsprj.common.PagePath;
import com.snsprj.common.ServerResponse;
import com.snsprj.dao.UserMapper;
import com.snsprj.dto.User;
import com.snsprj.security.User.UserService;
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


    @RequestMapping(value = "auth/login" ,method={RequestMethod.POST})
    @ResponseBody
    public String login(HttpServletRequest request){

        Boolean result = iUserAuthService.login("123","123456");

        User user = iUserAuthService.getUserDetail(1);

        ObjectMapper mapper = new ObjectMapper();

        String resultUser = "";
        try {
            resultUser = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resultUser;
    }


    /**
     *
     * @return login page
     */
    @RequestMapping(value="auth/login",method = {RequestMethod.GET})
    public String Index(){

        return PagePath.userLogin;
    }

    /// TODO test ===================================================

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private  UserMapper userMapper;


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
