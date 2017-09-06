package com.snsprj.ldap0904;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class Ldap {
    
//  private static final String sfUrl = "ldap://AD-Ldap-App.int.sfdc.com.cn:389";
  // 10.116.57.151
  private static final String sfUrl = "ldap://10.116.57.151:389";

    public static void main(String[] args) {
        
        String adminName = "SYS.MDM";
        String adminPwd = "believe2017";
        String base ="DC=SF,DC=com";
        
        String dn = "cn=" + adminName + "," + "CN=Users," + base;

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        
        env.put(Context.PROVIDER_URL, sfUrl);
        // // LDAP访问安全级别(none,simple,strong)
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, adminName);
        env.put(Context.SECURITY_CREDENTIALS, adminPwd);
        env.put("com.sun.jndi.ldap.connect.timeout", "5000");
        env.put("java.naming.ldap.attributes.binary", "objectGUID"); // 解决GUID乱码

        try {
            // 连接LDAP进行认证
            DirContext ctx = new InitialDirContext(env);
            System.out.println("认证成功");

            ctx.close();
        } catch (Exception e) {
            // javax.naming.AuthenticationException: [LDAP: error code 49 - 80090308: LdapErr:
            // DSID-0C0903A9, comment: AcceptSecurityContext error, data 52e, v1db0
            // 用户名或密码错误
            e.printStackTrace();
        }

    }

}
