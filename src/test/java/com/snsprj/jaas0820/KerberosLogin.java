package com.snsprj.jaas0820;

import com.sun.security.auth.callback.TextCallbackHandler;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by john on 2017/8/20.
 */
public class KerberosLogin {

    
    public static void main(String [] args){

//        String path = "/home/user/workspace/eclipse/ssm/src/test/java/com/snsprj/jaas0820";

        String path = "/workspace/idea/ssm/src/test/java/com/snsprj/jaas0802";

        System.setProperty("java.security.auth.login.config", path + "/jaas.conf");
        System.setProperty("java.security.krb5.conf", path + "/krb5.conf");
        System.setProperty("java.security.policy", path + "/krb5.policy");
        
        System.setSecurityManager(new SecurityManager());  
        
        LoginContext loginContext = null;

        try {
            loginContext = new LoginContext("kerberosLogin",new TextCallbackHandler());

        } catch (LoginException e) {
            e.printStackTrace();
        }

        try {
            loginContext.login();

        } catch (LoginException le) {
            System.err.println("Authentication failed:");
            System.err.println("  " + le.getMessage());
            System.exit(-1);
        }

    }
}
