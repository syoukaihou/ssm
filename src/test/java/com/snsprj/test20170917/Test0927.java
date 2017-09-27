package com.snsprj.test20170917;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Test0927 {


    @Test
    public void testGBK() {

        String str = "中文GBK";

        try {
            byte[] gbkByte = str.getBytes("GBK");

            String ret = bytes2HexString(gbkByte);
            System.out.println(ret);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }


}
