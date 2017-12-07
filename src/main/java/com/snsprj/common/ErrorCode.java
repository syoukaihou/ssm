package com.snsprj.common;

/**
 * Created by John on 2017/6/13.
 */
public final class ErrorCode {

    /**
     * 服务器内部错误
     */
    public static final Integer INTERNAL_SERVER_ERROR = 500;
    
    // =====================参数错误码=====================
    
    /**
     * 参数非法
     */
    public static final Integer ILLEGAL_ARGUMENT = 20002;


    // =====================用户认证授权错误码=====================
    
    /**
     * 用户名或密码错误
     */
    public static final Integer INCORRECT_USERNAME_OR_PASSWORD = 30002;

    /**
     * 账户被冻结
     */
    public static final Integer ACCOUNT_IS_BLOCKED = 30003;

    /**
     * 用户名已存在
     */
    public static final Integer ACCOUNT_ALREADY_EXISTS = 30004;

    
    /**
     * 父id无效
     */
    public static final Integer ILLEGAL_PARENTID = 40002;
    
    /**
     * 无效的邮箱地址
     */
    public static final Integer EMAIL_INVALID_ADDRESS = 50002;
}
