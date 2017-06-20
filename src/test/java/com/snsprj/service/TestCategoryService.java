package com.snsprj.service;

import com.snsprj.service.impl.CategoryServiceImpl;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by skh on 2017/6/12.
 */
public class TestCategoryService {


    public static void main(String [] args){

        try {
            TestCategoryService.testDate();
            System.out.println("解析成功！！");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public static void testDate() throws ParseException{
//        String dateStr = "2017-6-19 9:1:1";
        String dateStr = "2017-6-19 9:1";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = null;

        try {
            date = sdf1.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("第一次解析失败，尝试第二次解析");
            date = sdf2.parse(dateStr);
        }
    }
}
