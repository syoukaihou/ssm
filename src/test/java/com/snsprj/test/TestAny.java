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
//            HttpRequestUtil.doPost(url, dataMap);
            String result = HttpRequestUtil.doGet(url, dataMap);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
