package com.snsprj.jaas0822;

import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import com.sun.security.auth.callback.TextCallbackHandler;

/**
 * This JaasAcn application attempts to authenticate a user
 * and reports whether or not the authentication was successful.
 *
 * Created by skh on 2017/8/22.
 */
public class JaasAcn {
    public static void main(String[] args) {

        String path = "/workspace/idea/ssm/src/test/java/com/snsprj/jaas0822/";

        System.setProperty("java.security.auth.login.config", path + "jaas.conf");
//        System.setProperty("java.security.krb5.conf", path + "krb5.conf");

        System.setProperty("java.security.krb5.realm", "SNSPRJ.CN");
        // System.setProperty("java.security.krb5.kdc", "192.168.1.175");
        System.setProperty("java.security.krb5.kdc", "kerberos.snsprj.cn");

        // Obtain a LoginContext, needed for authentication. Tell it
        // to use the LoginModule implementation specified by the
        // entry named "JaasSample" in the JAAS login configuration
        // file and to also use the specified CallbackHandler.
        LoginContext lc = null;
        try {
            lc = new LoginContext("JaasSample", new TextCallbackHandler());

            // attempt authentication
            try {
                lc.login();
            } catch (LoginException le) {
                le.printStackTrace();
                System.err.println("Authentication failed:");
                System.err.println("  " + le.getMessage());
                System.exit(-1);
            }

        } catch (LoginException le) {
            System.err.println("Cannot create LoginContext. " + le.getMessage());

        } catch (SecurityException se) {
            System.err.println("Cannot create LoginContext. " + se.getMessage());
            System.exit(-1);
        }

        System.out.println("Authentication succeeded!");

    }
}
