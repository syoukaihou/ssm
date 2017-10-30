package com.snsprj.service;

/**
 * spring 邮件服务
 * @author john
 *
 */
public interface IMailService {

	public void htmlMail(String[] receiver, String subject, String html);
}
