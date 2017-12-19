package com.snsprj.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestStringUtils {

    @Test
    public void testSubstringBefore() {

        // String oldStr = "同福客栈酒店管理有限公司/财务部/后勤部/我改的/88888888";
        // String ret = StringUtils.substringBefore(oldStr, "88888888");

        String url = "http://www.baidu.com?name=xiao&age=27&";

        String result = StringUtils.substringBefore(url, "&");

        Assert.assertEquals("http://www.baidu.com?name=xiao", result);
    }

    @Test
    public void testSubstringBeforeLast() {

        String url = "http://www.baidu.com?name=xiao&age=27&";

        String result = StringUtils.substringBeforeLast(url, "&");

        Assert.assertEquals("http://www.baidu.com?name=xiao&age=27", result);
    }

    @Test
    public void testSubstringAfterLast() {
        String email = "asfg@daf@outlook.com.cn";
        email = "sdefas";

        String ret1 = StringUtils.substringAfterLast(email, "@");
        String ret2 = StringUtils.substringAfter(email, "@");

        System.out.println("ret1=" + ret1);
        System.out.println("ret2=" + ret2);
    }

    @Test
    public void testSplitPreserveAllTokens() {

        String record = "xxx@qq.com,xxx@qq.com,,研发部,";

        String[] strings = StringUtils.splitPreserveAllTokens(record, ",");

        Assert.assertEquals(5, strings.length);
    }

    @Test
    public void stringToAscii() {

        // 这里面有一个神奇的字符
        String value = "﻿";

        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        Assert.assertEquals("65279", sbu.toString());
    }



}
