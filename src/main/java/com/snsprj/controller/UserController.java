package com.snsprj.controller;

import com.snsprj.common.ErrorCode;
import com.snsprj.common.PagePath;
import com.snsprj.common.ServerResponse;
import com.snsprj.dto.User;
import com.snsprj.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;


/**
 * Created by skh on 2017/6/6.
 */
@Controller
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     *
     * @return register page
     */
    @RequestMapping(value = "auth/register",method = {RequestMethod.GET})
    public String getRegister(){

        return PagePath.register;
    }

    /**
     * post register
     * @return json
     */
    @RequestMapping(value = "auth/register",method = {RequestMethod.POST})
    public ServerResponse<User> postRegister(@RequestParam("username") String username,
                                             @RequestParam("password") String password){

        User user = null;
        try{
            user = iUserService.register(username,password);
        }catch (ConstraintViolationException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());

            return ServerResponse.createByError(-1);
        }


        return ServerResponse.createBySuccess(user);
    }

    /**
     *
     * @return login page
     */
    @RequestMapping(value="auth/login",method = {RequestMethod.GET})
    public String getLogin(){

        return PagePath.userLogin;
    }

    /**
     * post login
     * @return json
     */
    @RequestMapping(value = "auth/login" ,method={RequestMethod.POST})
    @ResponseBody
    public ServerResponse<User> postLogin(@RequestParam("username") String username,
                                      @RequestParam("password") String password){

        // 获取当前的subject
        Subject currentUser = SecurityUtils.getSubject();

        // 使用session
        Session session = currentUser.getSession();

        // 判断当前用户是否已被认证
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

            try{
                currentUser.login(usernamePasswordToken);
            } catch (AuthenticationException ex){
                if(ex instanceof UnknownAccountException || ex instanceof IncorrectCredentialsException){

                    // 用户名或密码错误
                    return ServerResponse.createByError(ErrorCode.INCORRECT_USERNAME_OR_PASSWORD);
                }

                // 其他错误，例如：用户被锁定
                return ServerResponse.createByError(ErrorCode.ACCOUNT_IS_BLOCKED);
            }

        }

        return ServerResponse.createBySuccess();
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
