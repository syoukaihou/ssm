package com.snsprj.controller;

import com.snsprj.common.ErrorCode;
import com.snsprj.common.ServerResponse;
import com.snsprj.service.ICategoryService;
import com.snsprj.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by skh on 2017/6/12.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value="/add")
    @ResponseBody
    public ServerResponse<CategoryVo> addCategory(@Valid CategoryVo categoryVo, Errors errors){

        if(errors.hasErrors()){
            return ServerResponse.createByError(ErrorCode.ILLEGAL_ARGUMENT);
        }
        int result = iCategoryService.insertCategory(categoryVo);

        if(result > 0){
            return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByError(ErrorCode.ILLEGAL_ARGUMENT);
        }

    }
}
