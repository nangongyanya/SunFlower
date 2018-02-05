package com.sunflower.back.domain.admin;

import java.io.Serializable;

/**
 * 角色和功能菜关联关系实体类
 * 
 * 类名称：AdminRoleUrl 创建时间：Jan 29, 2013
 * 
 * @version 1.0.0
 * 
 */
public class AdminRoleUrl implements Serializable {

	private static final long serialVersionUID = -4226456324312635565L;
	private Integer id;
	private String roleId;
	private String menuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
