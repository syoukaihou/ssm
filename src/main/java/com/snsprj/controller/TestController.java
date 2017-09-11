package com.snsprj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.snsprj.common.ServerResponse;
import com.snsprj.dao.ProductMapper;
import com.snsprj.dao.UserMapper;
import com.snsprj.dto.Product;
import com.snsprj.dto.User;
import com.snsprj.runnable.MyRunable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by skh on 2017/7/3.
 */
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    /// TODO test ===================================================

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapper productMapper;


    @RequestMapping(value = {"/test/user","test/get/user"})
    @ResponseBody
    public ServerResponse<User> getUser(){

        User user = userMapper.selectByPrimaryKey(1);

        ObjectMapper mapper = new ObjectMapper();

        return ServerResponse.createBySuccess(user);
    }


    @RequestMapping("/test/userdetial")
    @ResponseBody
    public ServerResponse<User> getUserDetial(){

        User user = userMapper.selectDetailByPrimaryKey(1);


        return ServerResponse.createBySuccess(user);
    }

    public ServerResponse<User> testPage(){

        PageHelper.startPage(1,10);

        return ServerResponse.createBySuccess();
    }



    @RequestMapping("/test/mybatis/if")
    @ResponseBody
    public String testMybatisIf(){

        ObjectMapper mapper = new ObjectMapper();

        Integer vane = 0;
        String dateStr = "2017-06-29 12:11:40";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Byte status = 2;
        List<Product> list = productMapper.selectByUpdatedAt(vane,date,status);

        String ret = null;
        try {
            ret = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @RequestMapping("test/runable")
    @ResponseBody
    public String testRunable(){

        Runnable runnable = new MyRunable("zhangsan");

        Thread thread = new Thread(runnable);

        thread.start();

        return "执行中。。。。。。。。";
    }

    // test redis
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    
    @RequestMapping("test/redis")
    @ResponseBody
    public String testRedis(){

        logger.info("开始测试redis--------");
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("user","age","20");
        
        ValueOperations<String, Object>  valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name", "foo");
        
        System.out.println("name ====" +  valueOperations.get("name") );

        return (String)hashOperations.get("user","age");
    }
    
    private static String path = "";
    static {
        path = "asdfasdf";
    }
    
    @RequestMapping(value = "test/static")
    @ResponseBody
    public String testStatic(){
        
        System.out.println("path ======" + path);
        return path;
    }
    
    @Value("${jdbc.username}")
    private String username;
    
    @RequestMapping(value = "test/properties")
    @ResponseBody
    public void testGetProperties(){
        System.out.println("jdbc 的用户名为===" + username);
    }

}
