package com.snsprj.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.snsprj.service.IMailService;

@Service("iMailService")
public class MailServiceImpl implements IMailService{


	@Autowired
	JavaMailSender javaMailSender;
	
	
	public void htmlMail(String[] receiver, String subject, String html){
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
        try {
	        messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	        messageHelper.setFrom("sy0ukaih0u@163.com");
	        messageHelper.setTo(receiver);

	        messageHelper.setSubject(subject);
	        // true 表示启动HTML格式的邮件
	        messageHelper.setText(html, true);
	        javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
	        e.printStackTrace();
        }
	}
    
}
