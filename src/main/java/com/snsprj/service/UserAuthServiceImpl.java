package com.snsprj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsprj.dao.UserMapper;
import com.snsprj.dto.User;

@Service("iUserAuthService")
public class UserAuthServiceImpl implements IUserAuthService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean login(String account, String password) {

		User user = userMapper.selectByPrimaryKey(1);
		
		if(user != null){
			return true;
		}
		return false;
	}

	public User getUserDetail(Integer userId){

		return userMapper.selectDetailByPrimaryKey(userId);
	}
}
