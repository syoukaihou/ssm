package com.snsprj.controller;

import com.github.pagehelper.PageInfo;
import com.snsprj.common.ServerResponse;
import com.snsprj.dto.Product;
import com.snsprj.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by skh on 2017/6/27.
 */

@Controller
@RequestMapping("/product")
@Validated
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("/list")
    @ResponseBody
    public ServerResponse<PageInfo<Product>> getProductList(@Min(value = 0, message = ("category_id错误！")) Integer category_id,
                                                   @NotNull Integer page_num) {

        // TODO 若 categoryId=0 则显示所有商品

        Byte status = 1;

        PageInfo<Product> page = iProductService.getProductByCategoryId(category_id, status, page_num, 10);


        return ServerResponse.createBySuccess(page);
    }

}
