package com.snsprj.controller;

import com.snsprj.common.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by skh on 2017/7/21.
 */
@Controller
public class BuildingController {

    @RequestMapping("/")
    public String building(){

        return PagePath.building;
    }
}
