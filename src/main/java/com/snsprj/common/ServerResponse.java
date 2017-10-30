package com.snsprj.common;

import java.io.Serializable;

/**
 * Created by John on 2017/6/11.
 */
public class ServerResponse<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7516291074736272338L;

	private int code;

    private String msg;

    private T data;

    private  ServerResponse(int code){
        this.code = code;
    }

    private  ServerResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private  ServerResponse(int code, T data){
        this.code = code;
        this.data = data;
    }

    private  ServerResponse(int code, String msg,T data){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public boolean isSuccess(){
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccess(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(int errorCode){
        return new ServerResponse<T>(errorCode);
    }
}
