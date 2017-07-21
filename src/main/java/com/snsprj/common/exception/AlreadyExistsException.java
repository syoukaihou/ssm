package com.snsprj.common.exception;

/**
 * Created by skh on 2017/7/21.
 *
 */
public class AlreadyExistsException extends Exception{

    public AlreadyExistsException(){
        super();
    }

    public AlreadyExistsException(String msg){
        super(msg);
    }

    public AlreadyExistsException(String msg,Throwable cause){
        super(msg,cause);
    }

    public AlreadyExistsException(Throwable cause){
        super(cause);
    }

}
