package com.snsprj.test;

import com.snsprj.utils.HttpRequestUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestAny {


    @Test
    public void testHttpClientPost(){

//        String url = "http://localhost:8080/ssm/test/httpclient";
        String url = "https://github.com";
        Map<String, String> dataMap = new HashMap<>();
//        dataMap.put("username","用户名");
//        dataMap.put("password","密码");
        try {
            HttpRequestUtil.doPost(url, dataMap);
            String result = HttpRequestUtil.doGet(url, dataMap);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHttpGet(){

        String url = "http://www.jnlzw.gov.cn/articles/ch00017/201801/81a82550-97cd-4370-a4a4-b698f31de74e.shtml";

        Map<String, String> dataMap = new HashMap<>();
        try {
            for (int i = 0; i<10; i++){
                String result = HttpRequestUtil.doGet(url, dataMap);
                Thread.sleep(1000);
            }
//            System.out.println(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
