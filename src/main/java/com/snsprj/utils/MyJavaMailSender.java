package com.snsprj.utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 线程安全的 org.springframework.mail.javamail.JavaMailSenderImpl 单例
 * @author skh
 * @Date 2017年11月6日
 *
 */
public class MyJavaMailSender extends JavaMailSenderImpl{

	private static MyJavaMailSender instance;
	
	private MyJavaMailSender(){
		
	}
	
	public static MyJavaMailSender getInstance() {
		if (instance == null) {
			synchronized (MyJavaMailSender.class) {
				if (instance == null) {
					instance = new MyJavaMailSender();
				}
			}
		}
		return instance;
	}
	
}
