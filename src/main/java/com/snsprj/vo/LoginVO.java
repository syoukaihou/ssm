package com.snsprj.vo;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 登录页面数据对象
 * @author john
 *
 */
public class LoginVO {

	@NotNull(message="{用户名的长度为5到16位！}")
	@Size(min=5,max=16)
	private String username;
	
	@NotNull
	@Size(min=6,max=16)
	private String password;

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

}
