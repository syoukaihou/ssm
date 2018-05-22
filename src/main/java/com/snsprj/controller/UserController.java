package com.snsprj.controller;

import com.snsprj.common.ServerResponse;
import com.snsprj.dto.User;
import com.snsprj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by skh on 2017/6/6.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * post create user
     *
     * @return json
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public ServerResponse<User> postRegister(@RequestParam("username") String username,
                                             @RequestParam("password") String password) {

        return iUserService.register(username, password);
    }

}
