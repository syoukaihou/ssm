package com.snsprj.service.impl;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.snsprj.properties.PropertyPlaceholder;
import com.snsprj.service.IMailService;
import com.snsprj.utils.MyJavaMailSender;

@Service("iMailService")
public class MailServiceImpl implements IMailService{
	
	
	public void htmlMail(String[] receiver, String subject, String html){
		
		// TODO 从数据库中拿到邮件的配置信息
		String username = (String) PropertyPlaceholder.getProperty("mail.username");
		String password = (String) PropertyPlaceholder.getProperty("mail.password");
		String sender = (String) PropertyPlaceholder.getProperty("mail.username");
		String host = (String) PropertyPlaceholder.getProperty("mail.host");
		Integer port = Integer.valueOf((String)PropertyPlaceholder.getProperty("mail.port"));
		
		MyJavaMailSender javaMailSender = this.initMailSender(host, port, username, password);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
        try {
	        messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	        messageHelper.setFrom(sender);
	        messageHelper.setTo(receiver);

	        messageHelper.setSubject(subject);
	        // true 表示启动HTML格式的邮件
	        messageHelper.setText(html, true);
	        javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
	        e.printStackTrace();
        }
	}
	
	/**
	 * 
	 * @Author skh
	 * @Date 2017年11月6日
	 *
	 * @param host 邮件服务器地址
	 * @param port 邮件服务器端口
	 * @param username 邮件用户名
	 * @param password 邮件密码
	 * @return
	 */
	private MyJavaMailSender initMailSender(String host, Integer port, String username, String password){

		MyJavaMailSender mailSender = MyJavaMailSender.getInstance();
		
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        
		mailSender.setJavaMailProperties(properties);
		
		return mailSender;
	}
    
}
