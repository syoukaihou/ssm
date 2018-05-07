package com.snsprj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author SKH
 * @program sbsm
 * @description 测试用
 * @date 2018-04-28 16:20
 **/
@Controller
@RequestMapping(value = "/index")
public class IndexController {

    @RequestMapping(value = "/session")
    @ResponseBody
    public String testSession(HttpServletRequest request){

        HttpSession session = request.getSession();

        session.setAttribute("name", "xiaohb");

        return "OK";
    }

}
