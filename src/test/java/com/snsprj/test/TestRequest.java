package com.snsprj.test;

import java.io.IOException;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class TestRequest {

    @Test
    public void testSendPut(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "username=zhengkai0518%40thundersoft.com&password=e10adc3949ba59abbe56e057f20f883e&osVersion=android5");
        Request request = new Request.Builder()
          .url("http://192.168.7.108:8080/api/android/device/login/2486e7447c5830b006f6fa15686ace9b")
          .put(body)
          .addHeader("content-type", "application/x-www-form-urlencoded")
          .addHeader("cache-control", "no-cache")
          .addHeader("postman-token", "aff5a25c-4d84-e3d1-4a29-e3e60ce15314")
          .build();

        try {
            Response response = client.newCall(request).execute();
            
             JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            
             System.out.println(jsonObject.toJSONString());
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
