package com.sunflower.back.support.admin;

import java.util.Date;

import com.sunflower.common.base.BaseCriteria;

/**
 * 系统日志查询类类
 * 
 * 类名称：AdminLog 创建时间：Feb 7, 2018
 * 
 * @version 1.0.0
 * 
 */
public class AdminLogCriteria extends BaseCriteria {

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
}
