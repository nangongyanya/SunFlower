package com.sunflower.back.support.admin;

import java.util.Date;

import com.sunflower.common.base.BaseCriteria;

/**
 * 角色与管理员关系查询类
 * 
 * 类名称：AdminUserToRole 创建时间：2018-01-18
 * 
 * @version 1.0.0
 * 
 */
public class AdminUserToRoleCriteria extends BaseCriteria {

	private Integer roleId;
	private Integer adminId;
	private Date dateAdded;

	public AdminUserToRoleCriteria() {
	}

	public AdminUserToRoleCriteria(Integer roleId, Integer adminId, Date dateAdded) {
		super();
		this.roleId = roleId;
		this.adminId = adminId;
		this.dateAdded = dateAdded;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

}