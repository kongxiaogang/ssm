package com.kongxiaogang.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kongxiaogang.dao.UserRoleDao;
import com.kongxiaogang.model.UserRoleModel;
/**
 * 
 * <pre>项目名称：xhcf-back-dao    
 * 类名称：UserRoleDAOImpl    
 * 类描述：    
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2016年9月2日 下午4:07:49    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2016年9月2日 下午4:07:49    
 * 修改备注：       
 * @version </pre>
 */

public class UserRoleDaoImpl extends SqlSessionDaoSupport implements UserRoleDao {

	@Override
	public int deleteByPrimaryKey(int menuId) {
		return getSqlSession().update("");
	}

	@Override
	public int insert(UserRoleModel userRoleModel) {
		return getSqlSession().insert("UserRoleMapper.insert",userRoleModel);
	}

	@Override
	public int insertSelective(UserRoleModel record) {
		return 0;
	}

	@Override
	public UserRoleModel selectByPrimaryKey(int menuId) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UserRoleModel record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UserRoleModel record) {
		return 0;
	}
	@Override
	public int deleteByUserId(int userId) {
		return getSqlSession().delete("UserRoleMapper.deleteByUserId", userId);
	}


}
