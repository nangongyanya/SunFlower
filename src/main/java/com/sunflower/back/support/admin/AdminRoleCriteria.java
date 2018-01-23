package com.sunflower.back.support.admin;

/**
 * 管理员角色查询类
 * 
 * 类名称：AdminRoleCriteria 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public class AdminRoleCriteria {

	private Integer id;
	private String roleName;
	private String roleDesc;
	private String roleCode;

	public AdminRoleCriteria() {
	}

	public AdminRoleCriteria(String roleName, String roleDesc, String roleCode) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleCode = roleCode;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}