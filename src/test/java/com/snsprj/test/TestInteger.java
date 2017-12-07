package com.snsprj.test;

import org.junit.Test;

public class TestInteger {

    @Test
    public void testIntegerEqual() {

        Integer num1 = null;

        boolean result = this.integerEqual(num1, 0);

        System.out.println(result);
    }

    private boolean integerEqual(Integer num1, Integer num2) {

        if (num1 == num2) {
            return true;
        }
        return false;
    }

    @Test
    public void testIntegerMultiply() {

        Integer validTime = 5;
        long validTimeLong = validTime* 24 * 60 * 60 * 1000;

        System.out.println(validTimeLong);
    }
}
