package com.kongxiaogang.dao.impl;


import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kongxiaogang.dao.RolePerDao;
import com.kongxiaogang.model.RolePerModel;


/**
 * 
 * <pre>项目名称：xhcf-back-dao    
 * 类名称：RolePerDAOImpl    
 * 类描述：角色和权限关联信息
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2016年9月23日 上午9:11:17    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2016年9月23日 上午9:11:17    
 * 修改备注：       
 * @version </pre>
 */
public class RolePerDaoImpl extends SqlSessionDaoSupport implements RolePerDao {

	@Override
	public int deleteByPrimaryKey(int id) {
		return 0;
	}

	@Override
	public int insert(RolePerModel rolePerModel) {
		return getSqlSession().insert("RolePerMapper.insert",rolePerModel);
	}

	@Override
	public int insertSelective(RolePerModel record) {
		return 0;
	}

	@Override
	public RolePerModel selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(RolePerModel record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(RolePerModel record) {
		return 0;
	}

	@Override
	public int deleteByRoleId(int roleId) {
		return getSqlSession().delete("RolePerMapper.deleteByRoleId",roleId);
	}
}
