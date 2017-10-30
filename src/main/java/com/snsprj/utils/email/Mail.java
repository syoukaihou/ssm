package com.snsprj.utils.email;

import java.util.List;

/**
 * 邮件数据模型
 * @author skh
 * @Date 2017年10月30日
 *
 */
public class Mail {

	public static final String ENCODEING = "UTF-8";
	private String host; // 服务器地址
    private String port; // 端口
    private String sender; // 发件人的邮箱
    private List<String> receiver; // 收件人的邮箱
    /**
     * 发件人昵称
     */
    private String name; 
    private String username; // 账号
    private String password; // 密码
    private String subject; // 主题
    private String message; // 信息(支持HTML)
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public List<String> getReceiver() {
		return receiver;
	}
	public void setReceiver(List<String> receiver) {
		this.receiver = receiver;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
