package com.kongxiaogang.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kongxiaogang.dao.PermissionDao;
import com.kongxiaogang.model.PermissionModel;

/**
 * DAO实现示例
 * @author LiJZ
 *
 */
public class PermissionDaoImpl extends SqlSessionDaoSupport implements PermissionDao {
	@Override
	public PermissionModel getPermissionByPermissionName(String permissionName) {
		List<PermissionModel> res = getSqlSession().selectList("getPermission",permissionName);
		return res != null && res.size() > 0 ? res.get(0) : null;
	}

	@Override
	public int insert(PermissionModel permission) {
		getSqlSession().insert("insertPermission", permission);
		return permission.getPerId();
	}

	@Override
	public int updateByPrimaryKeySelective(PermissionModel permission) {
		return getSqlSession().update("PermissionModelMapper.updateByPrimaryKeySelective",permission);
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		return getSqlSession().delete("PermissionModelMapper.deleteByPrimaryKey", id);
	}

	@Override
	public PermissionModel getPermissionNameByPermissionID(String permissionID) {
		List<PermissionModel> res = getSqlSession().selectList("getPermissionByPermissionID",permissionID);
		return res != null && res.size() > 0 ? res.get(0) : null;
	}

	@Override
	public List<PermissionModel> selectPermissionList(PermissionModel permission) {
		return  getSqlSession().selectList("getPermissionList",permission);
	}

	@Override
	public List<Map<String,Object>> selectPermissionList(Map<String,Object> permission) {
		return  getSqlSession().selectList("getPermissionListByMap",permission);
	}
	
	@Override
	public List<PermissionModel> getLevelOnePermissionList() {
		return getSqlSession().selectList("selectLevelOnePermissions");
	}

	@Override
	public List<PermissionModel> getChildsPermissionByOpeId(Integer opeId) {
		return getSqlSession().selectList("selectChildsPermissionByOpeId",opeId);
	}

	@Override
	public int insertSelective(PermissionModel record) {
		return 0;
	}

	@Override
	public PermissionModel selectByPrimaryKey(int perId) {
		return null;
	}

	@Override
	public int updateByPrimaryKey(PermissionModel record) {
		return 0;
	}

}
