package com.snsprj.jaas;

import javax.naming.*;
import javax.naming.directory.*;
import javax.security.auth.login.*;
import javax.security.auth.Subject;

import java.util.Hashtable;

/**
 * Demonstrates how to create an initial context to an LDAP server using "GSSAPI" SASL
 * authentication (Kerberos v5). Requires J2SE 1.4, or JNDI 1.2 with ldapbp.jar, JAAS, JCE, an RFC
 * 2853 compliant implementation of J-GSS and a Kerberos v5 implementation. Uses
 * SampleCallbackHandler.
 *
 * usage: java -Djava.security.auth.login.config=gssapi_jaas.conf \
 * -Djava.security.krb5.conf=krb5.conf \ GssExample [qop [dn]]
 * 
 * The first property indicates which JAAS login module the application needs to use; the second
 * property is for configuration of the Kerberos subsystem.
 *
 * 'qop' is a comma separated list of tokens, each of which is one of auth, auth-int, or auth-conf.
 * If none is supplied, the default is 'auth'.
 */
class GssExample {

    public static void main(String[] args) {

        System.setProperty("java.security.auth.login.config",
                "/home/user/workspace/eclipse/ssm/src/test/java/com/snsprj/jaas/gsseg_jaas.conf");
        System.setProperty("java.security.krb5.conf",
                "/home/user/workspace/eclipse/ssm/src/test/java/com/snsprj/jaas/krb5.conf");
        System.setProperty("java.security.policy",
                "/home/user/workspace/eclipse/ssm/src/test/java/com/snsprj/jaas/krb5.policy");
        System.setSecurityManager(new SecurityManager());  

        // 1. Log in (to Kerberos)
        LoginContext lc = null;
        try {
            lc = new LoginContext("GssExample", new SampleCallbackHandler());

            // Attempt authentication
            // You might want to do this in a "for" loop to give
            // user more than one chance to enter correct username/password
            lc.login();

        } catch (LoginException le) {
            System.err.println("Authentication attempt failed" + le);
            System.exit(-1);
        }catch(Exception e){
            e.printStackTrace();
        }

        // 2. Perform JNDI work as logged in subject
        Subject.doAs(lc.getSubject(), new JndiAction(args));
    }
}


/**
 * The application must supply a PrivilegedAction that is to be run inside a Subject.doAs() or
 * Subject.doAsPrivileged().
 */
class JndiAction implements java.security.PrivilegedAction {
    private String[] args;

    public JndiAction(String[] origArgs) {
        this.args = (String[]) origArgs.clone();
    }

    public Object run() {
        performJndiOperation(args);
        return null;
    }

    private static void performJndiOperation(String[] args) {
        String dn;

        // Set up environment for creating initial context
        Hashtable<String,String> env = new Hashtable<>(11);

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

        // Must use fully qualified hostname
        //  env.put(Context.PROVIDER_URL, "ldap://ldap.jnditutorial.org:389/o=JndiTutorial");
        env.put(Context.PROVIDER_URL, "ldap://192.168.7.184:389");

        // Request the use of the "GSSAPI" SASL mechanism
        // Authenticate by using already established Kerberos credentials
        env.put(Context.SECURITY_AUTHENTICATION, "GSSAPI");

        // Optional first argument is comma-separated list of auth, auth-int,
        // auth-conf
        if (args.length > 0) {
            env.put("javax.security.sasl.qop", args[0]);
            dn = args[1];
        } else {
            dn = "";
        }

        try {
            /* Create initial context */
            DirContext ctx = new InitialDirContext(env);

            System.out.println(ctx.getAttributes(dn));

            // do something useful with ctx

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
