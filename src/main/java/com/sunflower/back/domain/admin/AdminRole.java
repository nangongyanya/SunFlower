package com.sunflower.back.domain.admin;

/**
 * 管理员角色实体类
 * 
 * 类名称：AdminRole 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public class AdminRole implements java.io.Serializable {

	private static final long serialVersionUID = -498742852127752071L;
	private Integer id;
	private String roleName;
	private String roleDesc;
	private String roleCode;

	public AdminRole() {
	}

	public AdminRole(String roleName, String roleDesc, String roleCode) {
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