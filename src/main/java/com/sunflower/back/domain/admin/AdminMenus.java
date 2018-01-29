package com.sunflower.back.domain.admin;

import java.io.Serializable;
import java.util.Set;

/**
 * 功能菜单实体类
 * 
 * 类名称：AdminMenus 创建时间：Jan 29, 2013
 * 
 * @version 1.0.0
 * 
 */
public class AdminMenus implements Serializable {

	private static final long serialVersionUID = 7205001437798380729L;
	private Integer id;
	private String name;
	private String url;
	private Integer sort;
	private String description;
	private Integer parentId;
	private AdminMenus adminMenus;
	private Set<AdminMenus> adminMenuss;
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public AdminMenus getAdminMenus() {
		return adminMenus;
	}

	public void setAdminMenus(AdminMenus adminMenus) {
		this.adminMenus = adminMenus;
	}

	public Set<AdminMenus> getAdminMenuss() {
		return adminMenuss;
	}

	public void setAdminMenuss(Set<AdminMenus> adminMenuss) {
		this.adminMenuss = adminMenuss;
	}
}
