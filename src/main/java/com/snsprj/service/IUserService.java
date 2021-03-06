package com.snsprj.service;

import com.snsprj.common.ServerResponse;
import com.snsprj.common.exception.AlreadyExistsException;
import com.snsprj.dto.User;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by skh on 2017/6/26.
 */
public interface IUserService {

    /**
     * register
     *
     * @param username username
     * @param password password
     * @return userId
     */
    public ServerResponse<User> register(@NotBlank(message = "用户名不能为空") String username,
            @NotBlank(message = "密码不能为空") String password);

}
