package com.snsprj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snsprj.dto.User;
import com.snsprj.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by skh on 2017/6/6.
 */
@Controller
public class IndexController {

    @Autowired
    private IUserAuthService iUserAuthService;


    @RequestMapping(value = "/index")
    @ResponseBody
    public String login(){

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


    @RequestMapping(value="test/map")
    public String testMap(ModelMap map){

        Map<String,String> userMap = new HashMap<>();
        userMap.put("nickname","西门吹雪");
        map.addAttribute("user",userMap);

        return "jsps/hello";
    }



}
