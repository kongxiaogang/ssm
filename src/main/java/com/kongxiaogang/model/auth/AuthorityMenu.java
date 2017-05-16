package com.kongxiaogang.model.auth;

import java.util.List;
/**
 * 
 * <pre>项目名称：xhcf-back-model    
 * 类名称：AuthorityMenu    
 * 类描述：权限菜单 （目前用于用户登录后具有的左侧菜单树）    
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2016年9月25日 下午3:29:02    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2016年9月25日 下午3:29:02    
 * 修改备注：       
 * @version </pre>
 */
public class AuthorityMenu {
	
	private Integer perId; //权限id
	
	private Integer menuId; //菜单id
	
	private String menuName; //菜单名称
	
	private String itemIcon; //菜单图标
	
	private String menuPageurl; //菜单url
	
	private Integer parentId;//父权限id
	
	private List<AuthorityMenu> childrens; //子菜单
	
	public AuthorityMenu(Integer perId,Integer menuId, String menuName, String itemIcon, String menuPageurl){
		this.perId = perId;
		this.menuId = menuId;
		this.menuName = menuName;
		this.itemIcon = itemIcon;
		this.menuPageurl = menuPageurl;
	}
	
	public AuthorityMenu(Integer perId,Integer menuId, String menuName, String itemIcon, String menuPageurl,Integer parentId){
		this.perId = perId;
		this.menuId = menuId;
		this.menuName = menuName;
		this.itemIcon = itemIcon;
		this.menuPageurl = menuPageurl;
		this.parentId = parentId;
	}
	
	public AuthorityMenu(Integer perId,Integer menuId, String menuName,  String itemIcon, String menuPageurl, Integer parentId,List<AuthorityMenu> childrens){
		this.perId = perId;
		this.menuId = menuId;
		this.menuName = menuName;
		this.itemIcon = itemIcon;
		this.menuPageurl = menuPageurl;
		this.parentId = parentId;
		this.childrens = childrens;
	}
	
	public Integer getPerId() {
		return perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getItemIcon() {
		return itemIcon;
	}

	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}

	public String getMenuPageurl() {
		return menuPageurl;
	}

	public void setMenuPageurl(String menuPageurl) {
		this.menuPageurl = menuPageurl;
	}

	public List<AuthorityMenu> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<AuthorityMenu> childrens) {
		this.childrens = childrens;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	
}