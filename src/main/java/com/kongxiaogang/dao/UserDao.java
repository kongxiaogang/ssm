package com.kongxiaogang.dao;

import com.kongxiaogang.model.UserModel;

public interface UserDao {
    int deleteByPrimaryKey(int userId);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(int userId);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);

	UserModel getUserByUserName(String userName);
}