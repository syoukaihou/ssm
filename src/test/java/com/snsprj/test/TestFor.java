package com.snsprj.test;

import java.util.Random;

import org.junit.Test;

public class TestFor {

    @Test
    public void testFor() {

        for (int i = 0; i < 10; i++) {

            boolean isError = false;
            inner: for (int j = 0; j < 10;) {

                Random random = new Random();

                int randomNum = random.nextInt();
                System.out.println("random num is " + randomNum);
                if (randomNum > 5) {
                    isError = true;
                    j++;
                    break inner;
                } else {
                    isError = false;
                    System.out.println("inner output " + j);
                    j++;
                    break inner;
                }
            }

            if (isError == true) {
                continue;
            }

            System.out.println("==============outer output " + i);
        }
    }

    @Test
    public void testWhileFor() {

        int i = 1;
        while (i < 5) {
            boolean isError = false;

            inner: for (int j = 0; j < 1; j++) {

                Random random = new Random();
                if (random.nextInt() > 5) {
                    isError = true;
                    break inner;
                }
                isError = false;
                System.out.println("inner output " + j);
            }

            if (isError == true) {
                i ++;
                continue;
            }

            System.out.println("==============outer output " + i);
            i ++;
        }
    }
}
