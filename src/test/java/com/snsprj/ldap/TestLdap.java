package com.snsprj.ldap;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * Created by john on 2017/8/16.
 */
public class TestLdap {

    public static void main(String[] args){
//        testLogin();
        testSupportedSASLMechanisms();
    }

    private static void testSupportedSASLMechanisms(){
        String LDAP_URL = "ldap://192.168.1.237:389";
        try {
            // Create initial context
            DirContext ctx = new InitialDirContext();

            // Read supportedSASLMechanisms from root DSE
            Attributes attrs = ctx.getAttributes(
                    LDAP_URL, new String[]{"supportedSASLMechanisms"});

            System.out.println(attrs);

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static void testLogin(){
        String LDAP_URL = "ldap://192.168.1.237:389";
        String account = "zhangsan"; // 模拟用户名
        String password = "123456"; // 模拟密码
            Hashtable<String,String> env = new Hashtable<>();
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_CREDENTIALS, password);
            // cn=属于哪个组织结构名称，ou=某个组织结构名称下等级位置编号
            env.put(Context.SECURITY_PRINCIPAL, account);
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, LDAP_URL);
            try {
                // 连接LDAP进行认证
                DirContext ctx = new InitialDirContext(env);
                System.out.println("认证成功");

            } catch (Exception e){
                e.printStackTrace();
            }

    }

}
