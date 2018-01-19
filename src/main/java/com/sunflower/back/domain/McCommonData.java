package com.sunflower.back.domain;

import java.util.Date;

public class McCommonData implements java.io.Serializable {

	private static final long serialVersionUID = 6527662743298513875L;
	private Integer id;
	private String name;
	private Integer sort;
	private Integer status;
	private Integer type;
	private Date dateAdded;
	private Date lastModified;
	
	private McCommonDataType mcCommonDataType;

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public McCommonDataType getMcCommonDataType() {
		return mcCommonDataType;
	}

	public void setMcCommonDataType(McCommonDataType mcCommonDataType) {
		this.mcCommonDataType = mcCommonDataType;
	}
	
}
