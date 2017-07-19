package com.snsprj.dao;

import com.snsprj.dto.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // insert by xiaohb 2017/6/15 start

    /**
     * 根据主键获取用户详细信息
     * @param id primary key
     * @return User
     */
    User selectDetailByPrimaryKey(Integer id);

    /**
     *
     * @param account account
     * @return User
     */

    User selectByUsername(String account);
}