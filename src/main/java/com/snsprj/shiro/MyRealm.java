package com.snsprj.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by John on 2017/6/25.
 */
class MyRealm extends AuthorizingRealm {
    /**
     * Retrieves the AuthorizationInfo for the given principals from the underlying data store.  When returning
     * an instance from this method, you might want to consider using an instance of
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc) for the given
     * authentication token.
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param authenticationToken the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 角色的名字是自定义的

        // 行为权限 xxx:xxx，用于标识某个资源的某种操作。

        // 行为权限 user[type]:delete[action]:john[instance]，对user类型的john实例delete权限


        // 1、把AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;

        // 2、从UsernamePasswordToken中获取username
        String username = usernamePasswordToken.getUsername();

        // 3、从数据库中查询username对应的用户记录

        // 4、根据用户情况，构建AuthenticationInfo对象并返回
        // 以下信息是从数据库中获取的
        Object principal = "john";
        Object credentials = "123456";
        String realmName = getName();

        return new SimpleAuthenticationInfo(principal,credentials,realmName);
    }
}
