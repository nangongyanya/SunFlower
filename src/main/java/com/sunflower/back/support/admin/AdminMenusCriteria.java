package com.sunflower.back.support.admin;

import java.util.Set;

/**
 * 功能菜单查询类
 * 
 * 类名称：AdminMenusCriteria 创建时间：Jan 29, 2013
 * 
 * @version 1.0.0
 * 
 */
public class AdminMenusCriteria {

	private String id;
	private String name;
	private String url;
	private String sort;
	private String description;
	private AdminMenusCriteria adminMenus;
	private Set<AdminMenusCriteria> adminMenuss;
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AdminMenusCriteria getAdminMenus() {
		return adminMenus;
	}

	public void setAdminMenus(AdminMenusCriteria adminMenus) {
		this.adminMenus = adminMenus;
	}

	public Set<AdminMenusCriteria> getAdminMenuss() {
		return adminMenuss;
	}

	public void setAdminMenuss(Set<AdminMenusCriteria> adminMenuss) {
		this.adminMenuss = adminMenuss;
	}
}
