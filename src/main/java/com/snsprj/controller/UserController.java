package com.snsprj.controller;

import com.snsprj.common.PagePath;
import com.snsprj.common.ServerResponse;
import com.snsprj.dto.User;
import com.snsprj.service.IUserAuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public ServerResponse<User> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password){

        // 获取当前的subject
        Subject currentUser = SecurityUtils.getSubject();

        // 使用session
        Session session = currentUser.getSession();
        // 测试当前用户是否已被认证
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

            try{
                currentUser.login(usernamePasswordToken);
            }catch (AuthenticationException ex){
                System.out.println("登录失败" + ex.getMessage());
            }
        }

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

    /**
     *
     * @return user index page
     */
    @RequestMapping(value = "/user/index", method={RequestMethod.GET})
    public String index(){


        return PagePath.index;
    }



}
