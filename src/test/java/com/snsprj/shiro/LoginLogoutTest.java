package com.snsprj.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by skh on 2017/7/3.
 */
public class LoginLogoutTest {


    @Test
    public void testHelloworld(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test.ini");

        SecurityManager securityManager = factory.getInstance();

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            subject.login(token);
        }catch (AuthenticationException e){

        }

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();




    }
}
