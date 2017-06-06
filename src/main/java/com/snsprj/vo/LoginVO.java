package com.snsprj.vo;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 登录页面数据对象
 * @author john
 *
 */
public class LoginVO {

    @NotEmpty(message="用户名不能为空！")
	@Size(min=5,max=16,message="用户名的长度为5到16位！")
	private String username;
	
	@NotEmpty(message="密码不能为空！")
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
