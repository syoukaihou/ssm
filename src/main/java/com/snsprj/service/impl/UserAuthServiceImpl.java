package com.snsprj.service.impl;

import com.snsprj.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsprj.dao.UserMapper;
import com.snsprj.dto.User;

@Service("iUserAuthService")
public class UserAuthServiceImpl implements IUserAuthService {

	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private UserMapper userMapper;
	
	@Override
	public boolean login(String account, String password) {

		User user = userMapper.selectByPrimaryKey(1);

        return user != null;
    }

	public User getUserDetail(Integer userId){

		return userMapper.selectDetailByPrimaryKey(userId);
	}
}