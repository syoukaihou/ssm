package com.snsprj.service;

import com.snsprj.dto.User;

public interface IUserAuthService {

	public boolean login(String account,String password);

	public User getUserDetail(Integer id);
}
