package com.snsprj.service;

import com.snsprj.service.impl.CategoryServiceImpl;
import org.junit.Test;

/**
 * Created by skh on 2017/6/12.
 */
public class TestCategoryService {

    @Test
    public void testGetCategotyDepth(){
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        int depth = categoryService.getCategotyDepth(8);

        System.out.println("产品深度为：" + depth);
    }
}
