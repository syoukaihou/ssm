package com.snsprj.ldap;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

/**
 * Created by john on 2017/8/16.
 */
public class TestLdap {

    private static final String companyUrl = "ldap://192.168.7.241:389";

    private static final String partnerUrl = "ldap://192.168.7.180:389";
    
    private static final String virtualboxUrl = "ldap://192.168.7.184:389";

    private static final String vmUrl = "ldap://192.168.1.237:389";
    
    private static final String aliUrl = "ldap://101.132.37.54:389";
    
    private static final String partnerUsername = "cn=admin,dc=thundersoft,dc=local";
    private static final String partnerPwd = "thundersoft";

    @Test
    public void testSupportedSASLMechanisms() {

        try {
            // Create initial context
            DirContext ctx = new InitialDirContext();

            // Read supportedSASLMechanisms from root DSE
//            Attributes attrs =  ctx.getAttributes(partnerUrl, new String[] {"supportedSASLMechanisms"});
            
            Attributes attrs =  ctx.getAttributes(virtualboxUrl, new String[] {"supportedSASLMechanisms"});

            // {supportedsaslmechanisms=supportedSASLMechanisms: DIGEST-MD5, CRAM-MD5, NTLM}
            System.out.println(attrs);

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {

//        String adminName = "QUARKDATA\\Administrator";
//        String adminPwd = "1qaz2wsx2017!";
        
//        String adminName = "tongxiangyu@snsprj.com";
//        String adminPwd = "XiaohuaibaoTel13120000287";
        
        String adminName = "xiaohuaibao@snsprj.com";
        String adminPwd = "TongxiangyuTel13120000288";

//        String adminName = "xiaohuaibao@corp.snsprj.com";
//        String adminPwd = "xiaohuaibao";

//        String adminName = "AD\\Administrator";
//        String adminPwd = "Xiaohuaibao010%";
        
        
        // tongxiangyu@snsprj.com
        // XiaohuaibaoTel13120000287
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        
//        env.put(Context.PROVIDER_URL, companyUrl);
        env.put(Context.PROVIDER_URL, virtualboxUrl);
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

    @Test
    public void testLdapMD5Encode(){

        String  charset = "UTF-8";
        
        // 16位MD5加密
        String relust16 = DigestUtils.md5Hex("TongxiangyuTel13120000288").substring(8,24);
        
        System.out.println("16位MD5加密：" + relust16);
        
        // base64编码
        try {
            byte[] data = Base64.encodeBase64(relust16.getBytes(charset));
            String result = new String(data,charset);
            System.out.println("{MD5}" + result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testMD5Login() {
//        String encodePwd = "{MD5}OWRmNzEyZTMwNmJiMGJhMA==";
        String vbPwd = "{MD5}NWFmNDdkZmNhODY4ZWU5ZQ==";
        
        // Set up environment for creating initial context
        Hashtable<String, Object> env = new Hashtable<String, Object>(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, virtualboxUrl);

        // Authenticate as C. User and password "mysecret"
        env.put(Context.SECURITY_AUTHENTICATION, "DIGEST-MD5");

        env.put(Context.SECURITY_PRINCIPAL, "xiaohuaibao@snsprj.com");
        env.put(Context.SECURITY_CREDENTIALS, vbPwd);

        env.put("com.sun.jndi.ldap.trace.ber", System.out);

        try {
            // Create initial context
            DirContext ctx = new InitialDirContext(env);

//            System.out.println(ctx.lookup("ou=NewHires"));

            // do something useful with ctx

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            //javax.naming.AuthenticationException: [LDAP: error code 49 - SASL(-13): user not found: no secret in database]
            e.printStackTrace();
        }
    }
    
    
    
    
}
