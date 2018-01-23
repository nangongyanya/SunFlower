package com.sunflower.back.domain.admin;

import java.util.Date;

/**
 * 角色与管理员关系实体类
 * 
 * 类名称：AdminUserToRole 创建时间：2018-01-18
 * 
 * @version 1.0.0
 * 
 */
public class AdminUserToRole implements java.io.Serializable {

	private static final long serialVersionUID = -2878992897691662704L;
	private Integer roleId;
	private Integer adminId;
	private Date dateAdded;

	public AdminUserToRole() {
	}

	public AdminUserToRole(Integer roleId, Integer adminId, Date dateAdded) {
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