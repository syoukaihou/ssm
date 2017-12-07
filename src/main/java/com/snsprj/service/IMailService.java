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
//	public void htmlMail(String[] receiver, String subject, String html);
	

	/**
	 * 使用模板发送邮件
	 * @Author skh
	 * @Date 2017年11月21日
	 *
	 * @param receiver 收件人
	 * @param subject 邮件主题
	 * @param map 模板填充数据
	 * @param templateName 模板名称
	 * @return
	 */
	public Integer sendMailByTemplate(String[] receiver, String subject,  Map<String,String> map, String templateName);
}
