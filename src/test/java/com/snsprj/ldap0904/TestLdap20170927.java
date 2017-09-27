package com.snsprj.ldap0904;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class TestLdap20170927 {
//    private static final String sfUrl = "ldap://10.202.149.121:389";
    private static final String sfUrl = "ldap://192.168.7.89:389";

    public static void main(String[] args) {
        // String adminName = "sf\\MDMLadp2";
//        String adminName = "cn=sf\\MDMLadp2,CN=Users,DC=SF,DC=com";
//        String adminPwd = "believe2017@";
        
        String adminName = "tongxy@snsprj.cn";
        String adminPwd = "Tongxiangyu88";

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, sfUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, adminName);
        env.put(Context.SECURITY_CREDENTIALS, adminPwd);
        env.put("com.sun.jndi.ldap.connect.timeout", "5000");
        env.put("java.naming.ldap.attributes.binary", "objectGUID"); // 解决GUID乱码

        try {
            DirContext ctx = new InitialDirContext(env);
            System.out.println("login success!");

            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
