package com.snsprj.jaas0820;

import com.sun.security.auth.callback.TextCallbackHandler;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by john on 2017/8/20.
 */
public class KerberosLogin {



    public void login(){

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
