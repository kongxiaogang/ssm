package com.kongxiaogang.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kongxiaogang.dao.UserDao;
import com.kongxiaogang.model.UserModel;

/**
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public int deleteByPrimaryKey(int userId) {
		return getSqlSession().delete("com.kongxiaogang.dao.UserModelMapper.deleteByPrimaryKey",userId);
	}

	@Override
	public int insert(UserModel record) {
		return getSqlSession().insert("com.kongxiaogang.dao.UserModelMapper.insert",record);
	}

	@Override
	public int insertSelective(UserModel record) {
		return getSqlSession().insert("com.kongxiaogang.dao.UserModelMapper.insertSelective",record);
	}

	@Override
	public UserModel selectByPrimaryKey(int userId) {
		System.out.println("执行");
		//getSqlSession().selectList("UserModelMapper.selectByPrimaryKey",userId);
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UserModel record) {
		return getSqlSession().update("com.kongxiaogang.dao.UserModelMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(UserModel record) {
		return getSqlSession().update("com.kongxiaogang.dao.UserModelMapper.updateByPrimaryKey",record);
	}

	@Override
	public UserModel getUserByUserName(String userName) {
		return getSqlSession().selectOne("UserModelMapper.getUserByUserName",userName);
	}

}
