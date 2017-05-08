package com.snsprj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsprj.dao.UserDTOMapper;
import com.snsprj.dto.UserDTO;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	UserDTOMapper userDTOMapper;
	
	@Override
	public boolean login(String account, String password) {

		UserDTO userDTO = userDTOMapper.selectByPrimaryKey(1);
		
		if(userDTO != null){
			return true;
		}
		return false;
	}
}
