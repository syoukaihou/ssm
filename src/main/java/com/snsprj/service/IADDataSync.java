package com.snsprj.service;

import javax.naming.ldap.LdapContext;

/**
 * Created by john on 2017/9/10.
 */
public interface IADDataSync {

    /**
     * AD登录
     * @param adUrl adUrl
     * @param username username
     * @param password password
     * @return LdapContext
     */
    public LdapContext adLogin(String adUrl, String username, String password);

    /**
     * 分页获取所有部门信息
     * @param ctx LdapContext
     * @param adDn String
     */
    public void getAllDept(LdapContext ctx,String adDn);

    /**
     * 分页获取AD中用户数据
     * @param ctx LdapContext
     * @param adDn String
     */
    public void getAllUser(LdapContext ctx,String adDn);
}
