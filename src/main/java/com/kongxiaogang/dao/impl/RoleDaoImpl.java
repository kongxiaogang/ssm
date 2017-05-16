package com.kongxiaogang.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kongxiaogang.dao.RoleDao;
import com.kongxiaogang.model.RoleModel;


/**
 * <pre>项目名称：xhcf-back-dao    
 * 类名称：RoleDAOImpl    
 * 类描述： 角色dao实现类
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2016年9月2日 下午2:10:21    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2016年9月2日 下午2:10:21    
 * 修改备注：       
 * @version </pre>
 */
public class RoleDaoImpl extends SqlSessionDaoSupport implements RoleDao {
	@Override
	public RoleModel getRoleByRoleName(String roleName) {
		List<RoleModel> res = getSqlSession().selectList("getRole",roleName);
		return res != null && res.size() > 0 ? res.get(0) : null;
	}

	@Override
	public int insertSelective(RoleModel role) {
		getSqlSession().insert("RoleModelMapper.insertSelective", role);
		return role.getRoleId();
	}

	@Override
	public int updateByPrimaryKeySelective(RoleModel role) {
		return getSqlSession().update("RoleModelMapper.updateByPrimaryKeySelective",role);
	}

	@Override
	public int deleteByPrimaryKey(int roleId) {
		return getSqlSession().delete("RoleModelMapper.deleteByPrimaryKey", roleId);
	}

	@Override
	public RoleModel getRoleNameByRoleID(String roleID) {
		List<RoleModel> res = getSqlSession().selectList("getRoleByRoleID",roleID);
		return res != null && res.size() > 0 ? res.get(0) : null;
	}

	@Override
	public List<RoleModel> selectRoleList(RoleModel role) {
		return  getSqlSession().selectList("getRoleList",role);
	}
	@Override
	public List<Map<String,Object>>  selectRoleAndPerList(RoleModel role) {
		return  getSqlSession().selectList("getRoleAndPerList",role);
	}

	@Override
	public int insert(RoleModel record) {
		return 0;
	}

	@Override
	public RoleModel selectByPrimaryKey(int roleId) {
		return null;
	}

	@Override
	public int updateByPrimaryKey(RoleModel record) {
		return 0;
	}
}
