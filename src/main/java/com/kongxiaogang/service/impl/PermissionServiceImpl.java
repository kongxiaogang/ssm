package com.kongxiaogang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongxiaogang.dao.PermissionDao;
import com.kongxiaogang.dao.RoleDao;
import com.kongxiaogang.model.PermissionModel;
import com.kongxiaogang.model.RoleModel;
import com.kongxiaogang.model.auth.TreeNode;
import com.kongxiaogang.service.PermissionService;


/**
 * 业务系统接入Trace，必须添加相应类注解<br>
 *
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private RoleDao roleDao;
	
	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	/**
	 * 根据条件查询权限信息列表，如果条件为空则查询所有权限信息
	 */
	@Override
	public List<PermissionModel> getPermissionList(PermissionModel permission) {
		return permissionDao.selectPermissionList(permission);
	}

	/**
	 * 根据用户Id删除权限信息
	 */
	@Override
	public Integer deletePermission(String permissionId) {
		Integer permissionid = Integer.parseInt(permissionId);
		return permissionDao.deleteByPrimaryKey(permissionid);
	}

	/**
	 * 添加权限
	 */
	@Override
	public Integer addPermission(PermissionModel permission) {
		return permissionDao.insertSelective(permission);
	}

	/**
	 * 根據id找权限信息
	 */
	@Override
	public PermissionModel getPermissionById(String id) {
		if (null == id || "".equals(id)) {
			return new PermissionModel();
		}
		PermissionModel permission = new PermissionModel();
		permission.setPerId(Integer.parseInt(id));
		List<PermissionModel> permissions = permissionDao.selectPermissionList(permission);
		if (permissions.size() > 0) {
			return permissions.get(0);
		}
		return new PermissionModel();
	}

	/**
	 * 修改权限信息
	 */
	@Override
	public void editPermission(PermissionModel permission) {
		permissionDao.updateByPrimaryKeySelective(permission);
	}
	/**
	 * 查询所有权限
	 */
	@Override
	public List<TreeNode> getAllPermissionByMenu(PermissionModel permission) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		List<Map<String,Object>> allPermissionList = permissionDao.selectPermissionList(new HashMap<String,Object>());
		Map<String,TreeNode> allLevelTwoTreeMap = new LinkedHashMap<String,TreeNode>();
		Map<String,TreeNode> allLevelThreeTreeMap = new LinkedHashMap<String,TreeNode>();
		for(Map<String,Object> per : allPermissionList){
			if("1".equals(per.get("menuLevel").toString())) {//一级菜单
				TreeNode treeNode = new TreeNode(per.get("perId").toString(),per.get("perName").toString(),per.get("menuParentId").toString(),per.get("menuId").toString(),new ArrayList<TreeNode>());
				treeNodes.add(treeNode);
			}
			if("2".equals(per.get("menuLevel").toString())) {//二级菜单
				TreeNode treeNode = new TreeNode(per.get("perId").toString(),per.get("perName").toString(),per.get("menuParentId").toString(),per.get("menuId").toString(),new ArrayList<TreeNode>());
				allLevelTwoTreeMap.put(treeNode.getMenuId(),treeNode);
			}
			if("3".equals(per.get("menuLevel").toString())) {//三级菜单
				TreeNode treeNode = new TreeNode(per.get("perId").toString(),per.get("perName").toString(),per.get("menuParentId").toString(),per.get("menuId").toString(),null);
				allLevelThreeTreeMap.put(treeNode.getMenuId(),treeNode);
			}
		}
		//将所有三级菜单放在二级菜单的children
		for(Map.Entry<String,TreeNode> treeNode : allLevelThreeTreeMap.entrySet()) {
			TreeNode levelThreeNode = treeNode.getValue();
			allLevelTwoTreeMap.get(levelThreeNode.getParentId()).getNodes().add(levelThreeNode);
		}
		//将所有二级菜单放在一级菜单的children
		for(Map.Entry<String,TreeNode> treeNode : allLevelTwoTreeMap.entrySet()) {
			TreeNode levelTwoNode = treeNode.getValue();
			for(TreeNode levelOneTree : treeNodes) {
				if(levelTwoNode.getParentId().toString().equals(levelOneTree.getMenuId().toString())) {
					levelOneTree.getNodes().add(levelTwoNode);
				}
			}
		}
		return treeNodes;
	}

	@Override
	public List<TreeNode> getPermissionsByRoleId(String roleId) {
		RoleModel role = new RoleModel();
		role.setRoleId(Integer.parseInt(roleId));
		List<Map<String,Object>> selectPers = roleDao.selectRoleAndPerList(role);
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		List<Map<String,Object>> allPermissionList = permissionDao.selectPermissionList(new HashMap<String,Object>());
		Map<String,TreeNode> allLevelTwoTreeMap = new LinkedHashMap<String,TreeNode>();
		Map<String,TreeNode> allLevelThreeTreeMap = new LinkedHashMap<String,TreeNode>();
		for(Map<String,Object> per : allPermissionList){
			if("1".equals(per.get("menuLevel").toString())) {//一级菜单
				TreeNode treeNode = new TreeNode(per.get("perId").toString(),per.get("perName").toString(),per.get("menuParentId").toString(),per.get("menuId").toString(),new ArrayList<TreeNode>());
				//添加此权限是否被check
				for(Map<String,Object> selectPer:selectPers) {
					if(selectPer.get("menuId").toString().equals(treeNode.getMenuId())) {
						treeNode.getState().put("checked", "true");
					}
				}
				treeNodes.add(treeNode);
			}
			if("2".equals(per.get("menuLevel").toString())) {//二级菜单
				TreeNode treeNode = new TreeNode(per.get("perId").toString(),per.get("perName").toString(),per.get("menuParentId").toString(),per.get("menuId").toString(),new ArrayList<TreeNode>());
				//添加此权限是否被check
				for(Map<String,Object> selectPer:selectPers) {
					if(selectPer.get("menuId").toString().equals(treeNode.getMenuId())) {
						treeNode.getState().put("checked", "true");
					}
				}
				allLevelTwoTreeMap.put(treeNode.getMenuId(),treeNode);
			}
			if("3".equals(per.get("menuLevel").toString())) {//三级菜单
				TreeNode treeNode = new TreeNode(per.get("perId").toString(),per.get("perName").toString(),per.get("menuParentId").toString(),per.get("menuId").toString(),null);
				//添加此权限是否被check
				for(Map<String,Object> selectPer:selectPers) {
					if(selectPer.get("menuId").toString().equals(treeNode.getMenuId())) {
						treeNode.getState().put("checked", "true");
					}
				}
				allLevelThreeTreeMap.put(treeNode.getMenuId(),treeNode);
			}
		}
		//将所有三级菜单放在二级菜单的children
		for(Map.Entry<String,TreeNode> treeNode : allLevelThreeTreeMap.entrySet()) {
			TreeNode levelThreeNode = treeNode.getValue();
			allLevelTwoTreeMap.get(levelThreeNode.getParentId()).getNodes().add(levelThreeNode);
		}
		//将所有二级菜单放在一级菜单的children
		for(Map.Entry<String,TreeNode> treeNode : allLevelTwoTreeMap.entrySet()) {
			TreeNode levelTwoNode = treeNode.getValue();
			for(TreeNode levelOneTree : treeNodes) {
				if(levelTwoNode.getParentId().toString().equals(levelOneTree.getMenuId().toString())) {
					levelOneTree.getNodes().add(levelTwoNode);
				}
			}
		}
		
		/*
		
		
		List<PermissionModel> parentPers = permissionDao.getLevelOnePermissionList(); //查询所有一级树节点
		//将权限集合转换成树
		for(PermissionModel parentPer:parentPers) {
			TreeNode treeNode = new TreeNode();
			treeNode.setNodeid(String.valueOf(parentPer.getPerId()));
			treeNode.setText(parentPer.getPerName());
			//添加此权限是否被check
			for(Map<String,Object> selectPer:selectPers) {
				if(selectPer.get("perId").equals(parentPer.getPerId())) {
					treeNode.getState().put("checked", "true");
				}
			}
			treeNodes.add(treeNode);
			List<PermissionModel> childPers = permissionDao.getChildsPermissionByOpeId(parentPer.getMenuId());
			List<TreeNode> childtrees = new ArrayList<TreeNode>();
			//添加子节点
			for(PermissionModel childPer:childPers) {
				TreeNode childtree = new TreeNode();
				childtree.setNodeid(String.valueOf(childPer.getPerId()));
				childtree.setText(childPer.getPerName());
				childtree.setNodes(null);
				//添加此权限是否被check
				for(Map selectPer:selectPers) {
					if(selectPer.get("perId").equals(childPer.getPerId())) {
						childtree.getState().put("checked", "true");
					}
				}
				childtrees.add(childtree);
			}
			treeNode.setNodes(childtrees);
		}*/
		return treeNodes;
	}
	
}
