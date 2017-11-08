package com.snsprj.service;

import java.util.Map;

/**
 * spring 邮件服务
 * @author john
 *
 */
public interface IMailService {

	/**
	 * 发送html格式的邮件
	 * @Author skh
	 * @Date 2017年11月8日
	 *
	 * @param receiver
	 * @param subject
	 * @param html
	 */
	public void htmlMail(String[] receiver, String subject, String html);
	
	/**
	 * 填充邮件模板，获得邮件内容字符串
	 * @Author skh
	 * @Date 2017年11月8日
	 *
	 * @param map
	 * @param templateName 模板名称
	 * @return
	 */
	public String getMailText(Map<String,String> map, String templateName);
}
