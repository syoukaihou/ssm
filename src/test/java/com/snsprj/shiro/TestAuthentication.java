package com.snsprj.shiro;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.env.Environment;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by John on 2017/6/25.
 */
public class TestAuthentication {

    @Test
    public void testLoginAndLogout(){

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test.ini");




    }
}
