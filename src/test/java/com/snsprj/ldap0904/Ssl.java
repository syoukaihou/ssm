package com.snsprj.ldap0904;

import javax.naming.*;
import javax.naming.directory.*;

import java.util.Hashtable;

/**
 * Demonstrates how to create an initial context to an LDAP server using SSL. For this example to
 * work, JSSE must be installed and configured, and the issuer of the LDAP server's certificate must
 * be in the JSSE trust store.
 *
 * usage: java Ssl
 */
public class Ssl {
    
    /*
     * keytool用法
     * 
     * keytool -import -file server_cert.cer -keystore jssecacerts
     * keytool -import -file /usr/local/20170904.cer -keystore /usr/local/jssecacert.jks
     * 
     * 
     * keytool -list -keystore 20170904.jks
     * keytool -list -keystore /usr/local/jssecacert.jks
     * 
     */
    
    
    
    public static void main(String[] args) {
        
        //证书所在目录
        String cacertPath = "/usr/local/jssecacert.jks"; 
        String keyStorePassword = "123456";
        
        // ldap://localhost:636/o=JNDITutorial
        String url = "ldap://10.116.57.151:636";
        
        // cn=S. User, ou=NewHires, o=JNDITutorial
        String username = "sf\\SYS.MDM";
        String password = "believe2017@";
        

        System.setProperty("javax.net.ssl.trustStore", cacertPath);
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
        
        // Set up environment for creating initial context
        Hashtable<String, Object> env = new Hashtable<String, Object>(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);

        // Specify SSL
        env.put(Context.SECURITY_PROTOCOL, "ssl");

        // Authenticate as S. User and password "mysecret"
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, username);
        env.put(Context.SECURITY_CREDENTIALS, password);


        try {
            // Create initial context
            DirContext ctx = new InitialDirContext(env);

            System.out.println(ctx.lookup("ou=NewHires"));
            
            System.out.println("连接成功！");

            // ... do something useful with ctx

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

