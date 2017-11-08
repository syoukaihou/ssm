package com.snsprj.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.snsprj.common.PropertyPlaceholder;
import com.snsprj.service.IMailService;
import com.snsprj.utils.MyJavaMailSender;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("iMailService")
public class MailServiceImpl implements IMailService {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Override
	public void htmlMail(String[] receiver, String subject, String html) {

		// TODO 从数据库中拿到邮件的配置信息
		String username = PropertyPlaceholder.getProperty("mail.username");
		String password = PropertyPlaceholder.getProperty("mail.password");
		String sender = PropertyPlaceholder.getProperty("mail.username");
		String host = PropertyPlaceholder.getProperty("mail.host");
		Integer port = Integer.valueOf(PropertyPlaceholder.getProperty("mail.port"));
		boolean enableSSL = Boolean.parseBoolean(PropertyPlaceholder.getProperty("mail.smtp.ssl.enable"));

		MyJavaMailSender javaMailSender = this.initMailSender(host, port, username, password, enableSSL);
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
	 * @param host
	 *            邮件服务器地址
	 * @param port
	 *            邮件服务器端口
	 * @param username
	 *            邮件用户名
	 * @param password
	 *            邮件密码
	 * @param enableSSL
	 *            开启SSL加密
	 * @return
	 */
	private MyJavaMailSender initMailSender(String host, Integer port, String username, String password,
			boolean enableSSL) {

		MyJavaMailSender mailSender = MyJavaMailSender.getInstance();

		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.debug", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.ssl.enable", String.valueOf(enableSSL));

		mailSender.setJavaMailProperties(properties);

		return mailSender;
	}

	/**
	 * 填充邮件模板，获得邮件内容字符串
	 * @Author skh
	 * @Date 2017年11月8日
	 *
	 * @param map
	 * @param templateName 模板名称
	 * @return
	 */
	@Override
	public String getMailText(Map<String,String> map, String templateName) {
		
		String html = "";
		try {

			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return html;
	}
}
