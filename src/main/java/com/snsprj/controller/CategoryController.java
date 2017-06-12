package com.snsprj.controller;

import com.snsprj.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by skh on 2017/6/12.
 */
@Controller
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value="test/depth")
    @ResponseBody
    public String testGetCategoryDepth(){


        int detph = iCategoryService.getCategotyDepth(8);


        return Integer.toString(detph);
    }

    @RequestMapping(value="/test/add")
    @ResponseBody
    public String addCategory(){

        iCategoryService.insertCategory("汽车类",0);
        return "";
    }
}
