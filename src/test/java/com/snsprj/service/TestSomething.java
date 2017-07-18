package com.snsprj.service;

import org.junit.Test;

import java.math.BigDecimal;


/**
 * Created by skh on 2017/7/13.
 */
public class TestSomething {


    @Test
    public void testBigDecimal(){

        Integer price = 11;

        BigDecimal off = new BigDecimal(10);

        BigDecimal ret = off.multiply(new BigDecimal(price));

        System.out.println(ret);
    }

    @Test
    public void testJsonArray(){

        String jsonStr = "[{\"unit\":\"元/次\",\"price\":\"158.0\",\"name\":\"腰背推拿\",\"quantity\":1,\"thirdId\":\"6\"}]";

    }
}
