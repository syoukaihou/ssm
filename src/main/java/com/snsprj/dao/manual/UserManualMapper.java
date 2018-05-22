package com.snsprj.dao.manual;

import com.snsprj.dto.User;

public interface UserManualMapper {

    // insert by xiaohb 2017/6/15 start



    /**
     *
     * @param account account
     * @return User
     */

    User selectByUsername(String account);
}