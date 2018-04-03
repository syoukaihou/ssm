package com.snsprj.test;

import java.util.Date;

import org.junit.Test;

public class TestDate {

    @Test
    public void testGetTime() {
        Date now = new Date();

        // 获取到13位时间戳
        Integer isTop = Math.toIntExact(now.getTime() / 1000);
        System.out.println(isTop);
    }
}
