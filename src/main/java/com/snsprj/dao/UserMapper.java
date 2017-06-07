package com.snsprj.dao;

import com.snsprj.dto.User;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Tue Jun 06 16:31:12 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Tue Jun 06 16:31:12 CST 2017
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Tue Jun 06 16:31:12 CST 2017
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Tue Jun 06 16:31:12 CST 2017
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Tue Jun 06 16:31:12 CST 2017
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Tue Jun 06 16:31:12 CST 2017
     */
    int updateByPrimaryKey(User record);


    /**
     * 根据用户id获取用户详细信息
     * @param id userId
     * @return User
     */
    User selectDetailByPrimaryKey(Integer id);
}