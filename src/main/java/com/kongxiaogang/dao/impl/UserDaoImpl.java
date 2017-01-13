package com.kongxiaogang.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kongxiaogang.dao.UserDao;
import com.kongxiaogang.model.UserModel;

/**
 * DAO实现示例
 * @author LiJZ
 *
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public int insert(UserModel user) {
		getSqlSession().insert("insert", user);
		return user.getId();
	}

	@Override
	public int updateByPrimaryKey(UserModel user) {
		return getSqlSession().update("UserMapper.updateUser",user);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return getSqlSession().update("UserMapper.deleteUser", id);
	}

	@Override
	public UserModel selectByPrimaryKey(Integer userId) {
		List<UserModel> res = getSqlSession().selectList("selectByPrimaryKey",userId);
		return res != null && res.size() > 0 ? res.get(0) : null;
	}


}
