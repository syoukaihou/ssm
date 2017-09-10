package com.snsprj.service.impl;

import com.snsprj.service.IADDataSync;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.*;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by john on 2017/9/10.
 */
public class ADDataSyncImpl implements IADDataSync {

    /**
     * AD登录
     * @param adUrl adUrl
     * @param username username
     * @param password password
     * @return LdapContext
     */
    public LdapContext adLogin(String adUrl, String username, String password){

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

        env.put(Context.PROVIDER_URL, adUrl);
        // // LDAP访问安全级别(none,simple,strong)
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, username);
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put("com.sun.jndi.ldap.connect.timeout", "5000");
        env.put("java.naming.ldap.attributes.binary", "objectGUID"); // 解决GUID乱码

        try {
            // 连接LDAP进行认证
            LdapContext ctx = new InitialLdapContext(env,null);
            System.out.println("认证成功");

            return ctx;
        } catch (Exception e) {
            // 用户名或密码错误
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分页获取所有部门信息
     * @param ctx LdapContext
     * @param adDn String
     */
    public void getAllDept(LdapContext ctx,String adDn){

        String searchFilter = "(objectClass=organizationalUnit)";
        // 指定AD域用户返回属性
        String[] attrs={"distinguishedName", "objectGUID", "whenCreated", "whenChanged"};

        // 构建查询控制器
        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(attrs);

        // 分页大小
        int pageSize=900;

        byte[] cookie = null;

        try{
            ctx.setRequestControls((new Control[] { new PagedResultsControl(  pageSize, Control.CRITICAL) }) );

            do {
                NamingEnumeration<SearchResult> results = ctx.search(adDn, searchFilter, searchCtls);
                cookie = parseControls(ctx.getResponseControls());

                //
                while (results.hasMoreElements()) {

                    SearchResult sr = (SearchResult) results.next();
                    String distinguishedName = sr.getAttributes().get("distinguishedName").get().toString().trim();
                    String createdAt = sr.getAttributes().get("whenCreated").get().toString().trim();
                    String updatedAt = sr.getAttributes().get("whenChanged").get().toString().trim();
                    String guid = sr.getAttributes().get("objectGUID").get().toString();
                    System.out.println(distinguishedName);
                    System.out.println("createdAt:" + createdAt);
                    System.out.println("updatedAt:" + updatedAt);
                    System.out.println(guid);
                }

            }while ((cookie != null) && (cookie.length != 0));
            ctx.close();

        }catch (IOException | NamingException ex ){
            ex.printStackTrace();
        }
    }



    public void getAllUser(LdapContext ctx,String adDn){

        String searchFilter = "(objectClass=User)";

        // 指定AD域用户返回属性
        String[] returnedAtts = {"objectGUID", "sAMAccountName", "mail", "telephoneNumber",
                "displayName", "whenCreated", "whenChanged"};
        int pageSize=100;
        byte[] cookie = null;

        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(returnedAtts);

        try {
            ctx.setRequestControls((new Control[] { new PagedResultsControl(  pageSize, Control.CRITICAL) }) );

            do {
                NamingEnumeration<SearchResult> results = ctx.search(adDn, searchFilter, searchCtls);

                while(null!=results && results.hasMoreElements()) {
                    SearchResult sr = results.next();

                    String guid = sr.getAttributes().get("objectGUID").get().toString();
                    String sAMAccountName = sr.getAttributes().get("sAMAccountName").get().toString();
//                    String mail = sr.getAttributes().get("mail").get().toString();
//                    String telephoneNumber = sr.getAttributes().get("telephoneNumber").get().toString();
                    String displayName = sr.getAttributes().get("displayName").get().toString().trim();
                    String createdAt = sr.getAttributes().get("whenCreated").get().toString().trim();
                    String updatedAt = sr.getAttributes().get("whenChanged").get().toString().trim();

                    System.out.println(guid);
                    System.out.println(sAMAccountName);
//                    System.out.println(mail);
//                    System.out.println(telephoneNumber);
                    System.out.println(displayName);
                    System.out.println("createdAt:" + createdAt);
                    System.out.println("updatedAt:" + updatedAt);


                }

                cookie = parseControls(ctx.getResponseControls());
                ctx.setRequestControls(new Control[] { new PagedResultsControl(  pageSize, cookie, Control.CRITICAL) });
            }while ((cookie != null) && (cookie.length != 0));


        }catch (IOException | NamingException ex){
            ex.printStackTrace();
        }
    }

    private static byte[] parseControls(Control[] controls) throws NamingException {
        byte[] cookie = null;
        if (controls != null) {
            for (Control ctl:controls) {
                if (ctl instanceof PagedResultsResponseControl) {
                    PagedResultsResponseControl prrc = (PagedResultsResponseControl) ctl;
                    cookie = prrc.getCookie();
                }
            }
        }
        return (cookie == null) ? new byte[0] : cookie;
    }

    public static void main(String [] args){
        String adUrl = "ldap://192.168.1.237";
        String username = "tongxy@snsprj.cn";
        String password = "Tongxiangyu88";
        String adDn = "OU=同福客栈酒店管理有限公司,DC=snsprj,DC=cn";

        ADDataSyncImpl aDDataSyncImpl = new ADDataSyncImpl();

        LdapContext ctx = aDDataSyncImpl.adLogin(adUrl,username,password);


//        aDDataSyncImpl.getAllDept(ctx,adDn);
        aDDataSyncImpl.getAllUser(ctx,adDn);
    }





}
