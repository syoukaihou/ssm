package com.snsprj.service.impl;

import com.snsprj.common.ErrorCode;
import com.snsprj.common.ServerResponse;
import com.snsprj.dao.generated.UserMapper;
import com.snsprj.dao.manual.UserManualMapper;
import com.snsprj.dto.User;
import com.snsprj.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Created by skh on 2017/6/26.
 */
@Service("iUserServiceImpl")
@Validated
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserManualMapper userManualMapper;


    /**
     * register
     * @param username username
     * @param password password
     * @return userId
     */
    @Override
    public ServerResponse<User> register(@NotBlank(message = "用户名不能为空") String username,
                            @NotBlank(message = "密码不能为空") String password) {


        // check does username already exist
        User user = userManualMapper.selectByUsername(username);

        if(user != null){
            return ServerResponse.createByError(ErrorCode.ACCOUNT_ALREADY_EXISTS);
        }

        String algorithmName = "MD5";

        String salt = RandomStringUtils.randomAscii(16);

        // 加密次数，必须与配置文件中配置的加密次数一致
        int hashIterations = 64;

        User newUser = new User();

        // 获取加密后的密码
        String credentials = new SimpleHash( algorithmName, password, salt, hashIterations).toString();

        newUser.setAccount(username);
        newUser.setPassword(credentials);
        newUser.setSalt(salt);

        userMapper.insert(newUser);

        return ServerResponse.createBySuccess(newUser);
    }

}
