package com.snsprj.thread;

import java.util.List;
import java.util.Map;

import com.snsprj.service.IMailService;

public class MailThread extends Thread {


	private IMailService iMailService;

	/**
	 * 邮件list
	 */
	private List<String> mailList;
	
	/**
	 * 邮件模板填充键值对
	 */
	private Map<String, String> map;
	
	/**
	 * 邮件模板名称
	 */
	private String templateName;
	
	/**
	 * 邮件主题
	 */
	private String subject;
	
	public  MailThread(List<String> mailList, Map<String, String> map, String templateName, String subject, IMailService iMailService) {
		this.mailList = mailList;
		this.map = map;
		this.templateName = templateName;
		this.subject = subject;
		this.iMailService = iMailService;
	}

	@Override
	public void run(){
		String[] receiver = (String[]) mailList.toArray();
		String html = iMailService.getMailText(map, templateName);
		iMailService.htmlMail(receiver, subject, html);
	}
}
