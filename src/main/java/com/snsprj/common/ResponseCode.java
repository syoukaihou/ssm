package com.snsprj.common;

/**
 * Created by John on 2017/6/11.
 */
public enum ResponseCode {

    SUCCESS(200,"SUCCESS");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
