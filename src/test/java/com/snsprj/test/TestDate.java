package com.snsprj.test;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDate {

    private static final Logger logger = LoggerFactory.getLogger(TestDate.class);

    @Test
    public void testGetTime() {
        Date now = new Date();

        // 获取到13位时间戳
        Integer isTop = Math.toIntExact(now.getTime() / 1000);
        System.out.println(isTop);

        logger.info(now.getTime() + "");
    }
}
