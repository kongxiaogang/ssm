package com.kongxiaogang.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongxiaogang.dao.UserDao;
import com.kongxiaogang.model.UserModel;
import com.kongxiaogang.service.UserService;
import com.kongxiaogang.service.vo.ServiceVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserModel getUserByID(String userId) {
		return userDao.selectByPrimaryKey(Integer.parseInt(userId));
	}

	@Override
	public ServiceVO loginOnMaster(UserModel user) {
		return null;
	}

	@Override
	public ServiceVO<UserModel> loginOnSlave(UserModel user) {
		ServiceVO resultVO = new ServiceVO();
		UserModel return_user = userDao.selectByPrimaryKey(1);
		//UserModel return_user = userDao.getUserByUserName(user.getUserName());
		return resultVO;
	}

	@Override
	public List<UserModel> getUserList(UserModel user) {
		return null;
	}

	@Override
	public Integer deleteUser(String userID) {
		return null;
	}

	@Override
	public ServiceVO addUser(UserModel user) {
		return null;
	}

	@Override
	public void editUser(UserModel user) {
		
	}

	@Override
	public List<Map<String, Object>> getUserAndRoleList(UserModel user) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getUserAndRoleAndPerList(UserModel user) {
		return null;
	}

}
