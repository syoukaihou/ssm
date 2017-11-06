package com.snsprj.common;

/**
 * Created by John on 2017/6/13.
 */
public final class ErrorCode {

    // =====================参数错误码=====================
    // 非法的参数
    public static final int ILLEGAL_ARGUMENT = 20002;


    // =====================用户认证授权错误码=====================
    // 用户名或密码错误
    public static final int INCORRECT_USERNAME_OR_PASSWORD = 30002;

    // 账户被冻结
    public static final int ACCOUNT_IS_BLOCKED = 30003;

    // 用户名已存在
    public static final int ACCOUNT_ALREADY_EXISTS = 30004;

    
    /**
     * 父id无效
     */
    public static final int ILLEGAL_PARENTID = 40002;
}
