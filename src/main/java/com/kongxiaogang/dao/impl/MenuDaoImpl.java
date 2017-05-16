package com.kongxiaogang.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kongxiaogang.dao.MenuDao;
import com.kongxiaogang.model.MenuModel;


/**
 *
 */
public class MenuDaoImpl extends SqlSessionDaoSupport implements MenuDao {

	@Override
	public int deleteByPrimaryKey(int menuId) {
		return getSqlSession().update("MenuMapper.deleteByPrimaryKey", menuId);
	}

	@Override
	public int insert(MenuModel record) {
		return 0;
	}

	@Override
	public int insertSelective(MenuModel record) {
		return 0;
	}

	@Override
	public MenuModel selectByPrimaryKey(int menuId) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(MenuModel record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MenuModel record) {
		return 0;
	}
	/**
	 * 获取所有
	 */
	@Override
	public List<MenuModel> getLevelOneMenuList() {
		return getSqlSession().selectList("MenuMapper.listLevelOneMenuList",new MenuModel());
		
	}

	@Override
	public List<MenuModel> getChildMenusByParentId(int parentId) {
		return getSqlSession().selectList("MenuMapper.getChildMenusByParentId",parentId);
	}

	@Override
	public List<MenuModel> getThisLevelMenusByMenuId(Integer menuId) {
		return getSqlSession().selectList("MenuMapper.getThisLevelMenusByMenuId",menuId);
	}
	
	@Override
	public List<MenuModel> selectAllMenusList(MenuModel menu) {
		return getSqlSession().selectList("MenuMapper.getAllMenusByMenu",menu);
	}

}
