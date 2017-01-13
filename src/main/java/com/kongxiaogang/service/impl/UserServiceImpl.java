package com.kongxiaogang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongxiaogang.dao.UserDao;
import com.kongxiaogang.model.UserModel;
import com.kongxiaogang.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserModel getUserByID(String userId) {
		return userDao.selectByPrimaryKey(Integer.parseInt(userId));
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
