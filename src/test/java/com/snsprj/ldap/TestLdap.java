package com.snsprj.ldap;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.junit.Test;

import java.util.Hashtable;

/**
 * Created by john on 2017/8/16.
 */
public class TestLdap {

    private static final String companyUrl = "ldap://192.168.7.241:389";

    private static final String partnerUrl = "ldap://192.168.7.180:389";


    @Test
    public void testSupportedSASLMechanisms() {

        try {
            // Create initial context
            DirContext ctx = new InitialDirContext();

            // Read supportedSASLMechanisms from root DSE
            Attributes attrs =
                    ctx.getAttributes(partnerUrl, new String[] {"supportedSASLMechanisms"});

            // {supportedsaslmechanisms=supportedSASLMechanisms: DIGEST-MD5, CRAM-MD5, NTLM}
            System.out.println(attrs);

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {

        String adminName = "QUARKDATA\\Administrator";
        String adminPwd = "1qaz2wsx2017!";
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, companyUrl);
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
