package com.sunflower.back.support.admin;


/**
 * 角色和功能菜关联关系查询类
 * 
 * 类名称：AdminRoleUrlCriteria 创建时间：Jan 29, 2013
 * 
 * @version 1.0.0
 * 
 */
public class AdminRoleUrlCriteria {

	private String id;
	private String roleId;
	private String menuId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
