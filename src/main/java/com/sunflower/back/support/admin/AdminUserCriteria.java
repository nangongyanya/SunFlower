package com.sunflower.back.support.admin;

import java.util.Date;

/**
 * 管理员查询类
 * 
 * 类名称：AdminUserCriteria 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public class AdminUserCriteria {

	private Integer id;
	private String nickname;
	private String username;
	private String psw;
	private Date dateAdded;
	private Date lastLoginDate;
	private Integer loginCount;
	private String lastLoginIp;
	private boolean status;
	private Integer groupId;

	public AdminUserCriteria() {
	}

	public AdminUserCriteria(String nickname, String username, String psw,
			boolean status) {
		this.nickname = nickname;
		this.username = username;
		this.psw = psw;
		this.status = status;
	}

	public AdminUserCriteria(String nickname, String username, String psw,
			Date dateAdded, Date lastLoginDate, Integer loginCount,
			String lastLoginIp, boolean status, Integer groupId) {
		this.nickname = nickname;
		this.username = username;
		this.psw = psw;
		this.dateAdded = dateAdded;
		this.lastLoginDate = lastLoginDate;
		this.loginCount = loginCount;
		this.lastLoginIp = lastLoginIp;
		this.status = status;
		this.groupId = groupId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsw() {
		return this.psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public Date getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}