package com.sunflower.back.domain.admin;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志类
 * 
 * 类名称：AdminLog 创建时间：Feb 7, 2018
 * 
 * @version 1.0.0
 * 
 */
public class AdminLog implements Serializable {

	private static final long serialVersionUID = -2486173548799490896L;
	private Integer id;
	// 操作类
	private String classMethod;
	// 操作时间
	private Date optDate;
	// 操作ip
	private String optIp;
	// 操作类型：update、delete、save等
	private String optType;
	// 操作描述
	private String optDesc;
	// 备注
	private String remake;
	// 管理员名称
	private String adminName;
	// 管理员id
	private Integer adminId;
	// 管理员
	private AdminUser adminUser;

	public Integer getId() {
		return id;
	}

	public String getClassMethod() {
		return classMethod;
	}

	public void setClassMethod(String classMethod) {
		this.classMethod = classMethod;
	}

	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public String getOptIp() {
		return optIp;
	}

	public void setOptIp(String optIp) {
		this.optIp = optIp;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getOptDesc() {
		return optDesc;
	}

	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

}
