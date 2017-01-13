package com.kongxiaogang.dao;

import com.kongxiaogang.model.UserModel;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserModel record);

    UserModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UserModel record);
}