package com.snsprj.security.User;

import com.snsprj.dao.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

/**
 * Created by skh on 2017/6/15.
 */
public class UserService implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param s username
     * @return User
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        com.snsprj.dto.User userDTO = userMapper.selectByUsername(s);

        if(userDTO == null){
            return null;
        }

        String password = userDTO.getPassword();

        User user = new User(s,password,true,true,true,true, AuthorityUtils.NO_AUTHORITIES);

        return user;
    }
}
