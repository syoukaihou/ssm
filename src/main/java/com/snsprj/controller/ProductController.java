package com.snsprj.controller;

import com.google.common.primitives.Ints;
import com.snsprj.common.ServerResponse;
import com.snsprj.dto.Product;
import com.snsprj.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by skh on 2017/6/27.
 */

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private IProductService iProductService;


    @RequestMapping("list")
    @ResponseBody
    public ServerResponse<List<Product>> getProductList(HttpServletRequest request){

        // TODO 若 categoryId=0 则显示所有商品
//        Integer categoryId = Integer.valueOf(request.getParameter("category_id"));

        Integer categoryId = Ints.tryParse(request.getParameter("category_id"));

        Integer pageNum = Integer.valueOf(request.getParameter("page_num"));

        Byte status = 1;

        List<Product> productList = iProductService.getProductByCategoryId(categoryId,status,pageNum,10);


        return ServerResponse.createBySuccess(productList);
    }

}
