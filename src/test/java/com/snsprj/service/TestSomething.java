package com.snsprj.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;


/**
 * Created by skh on 2017/7/13.
 */
public class TestSomething {

    private static String path = "";
    
    static {
        path = "123123";
    }
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

    @Test
    public void testRandomStr(){
        String str = RandomStringUtils.randomAscii(16);

        System.out.printf(str);
    }

    @Test
    public void testRedis(){
        String host = "123.57.70.80";
        int port = 6379;
        String password = "Xhb123456";
        Jedis jedis = new Jedis(host,port);
        jedis.auth(password);

        System.out.println("test redis status -----> "+jedis.ping());
    }
    
    @Test
    public void testPath(){
        System.out.println(path);
    }
    
    @Test
    public void testSplit(){
        String str = "";
        
        String [] strArr = str.split("-");
        
        if(null != strArr){
            System.out.println(strArr.length);
            System.out.println(strArr[0]);
        }
        
    }
    
    
    
    
    
}
