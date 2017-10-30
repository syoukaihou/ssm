package com.snsprj.utils.email;

import java.util.Properties;

import javax.mail.Session;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author skh
 * @Date 2017年10月30日
 *
 */
public class MailToolImpl implements MailTool{

    private static final Logger logger = LoggerFactory.getLogger(MailToolImpl.class);
    
	public boolean send(Mail mail) {

        // 发送email
        HtmlEmail email = new HtmlEmail();

        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", mail.getHost());
            properties.setProperty("mail.smtp.port", mail.getPort());
            properties.setProperty("mail.smtp.auth", "true");
            // 开启TLS加密方式
            properties.setProperty("mail.smtp.starttls.enable", "true");
            // 添加信任的服务器
            properties.setProperty("mail.smtp.ssl.trust", mail.getHost());
            DefaultAuthenticator defaultAuthenticator = 
                    new DefaultAuthenticator(mail.getUsername(), mail.getPassword());
            Session session = Session.getInstance(properties,defaultAuthenticator);
            email.setMailSession(session);
            // 字符编码集的设置
            email.setCharset(Mail.ENCODEING);
            // 收件人的邮箱
            email.addTo(mail.getReceiver().toArray(
                    new String[mail.getReceiver().size()]));
            // 发送人的邮箱
            email.setFrom(mail.getSender(), mail.getName());

            // 要发送的邮件主题
            email.setSubject(mail.getSubject());
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg(mail.getMessage());
            // 发送
            email.send();

            if (logger.isDebugEnabled()) {
                logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
            }
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver() + " 失败");
            return false;
        }
    }
}
