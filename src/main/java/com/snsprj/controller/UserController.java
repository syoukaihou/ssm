package com.snsprj.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.snsprj.common.ErrorCode;
import com.snsprj.common.PagePath;
import com.snsprj.common.ServerResponse;
import com.snsprj.common.exception.AlreadyExistsException;
import com.snsprj.dto.User;
import com.snsprj.service.IUserService;


/**
 * Created by skh on 2017/6/6.
 */
@Controller
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 返回注册页面
     *
     * @return register page
     */
    @RequestMapping(value = "auth/register", method = {RequestMethod.GET})
    public String getRegister() {

        return PagePath.register;
    }

    /**
     * post register
     *
     * @return json
     */
    @RequestMapping(value = "auth/register", method = {RequestMethod.POST})
    public ServerResponse<User> postRegister(@RequestParam("username") String username,
                                             @RequestParam("password") String password) {

        User user = null;
        try {
            user = iUserService.register(username, password);
        } catch (ConstraintViolationException ex) {

            return ServerResponse.createByError(ErrorCode.ILLEGAL_ARGUMENT);

        } catch (AlreadyExistsException ex) {

            return ServerResponse.createByError(ErrorCode.ACCOUNT_ALREADY_EXISTS);
        }


        return ServerResponse.createBySuccess(user);
    }

    /**
     * 返回登录页面
     *
     * @return login page
     */
    @RequestMapping(value = "auth/login", method = {RequestMethod.GET})
    public String getLogin() {

        return PagePath.userLogin;
    }

    /**
     * @return user index page
     */
    @RequestMapping(value = "/user/index", method = {RequestMethod.GET})
    public String index() {


        return PagePath.INDEX;
    }


}
