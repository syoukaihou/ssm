package com.snsprj.ldap;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by john on 2017/8/16.
 */
public class TestEncode {


    private static final String baseStr = "123456";

    @Test
    public void testBase64(){
        try {
            String result = new BASE64Encoder().encode(baseStr.getBytes("UTF-8"));
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMD5(){

        String charsetName = "UTF-8";

        String relust = DigestUtils.md5Hex(baseStr);

        String relust16 = null;

        byte [] bytes = DigestUtils.md5(baseStr);

        relust16 = Hex.encodeHexString(bytes);


        System.out.println(relust);
        System.out.println(relust16);

        System.out.println("=========================================");


        try {
            byte [] bytes1 = baseStr.getBytes(charsetName);

            System.out.println(new String(bytes1,charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}

