package com.snsprj.test;

import org.junit.Test;

/**
 * 测试动态参数
 * @author SKH
 * 2017年11月30日
 *
 */
public class TestArgs {

    @Test
    public void test(){

        this.test(10001, "mobile", "telephone", "fax","address", "post", "grade");
    }

    private void test(Integer id, String... args ){
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
