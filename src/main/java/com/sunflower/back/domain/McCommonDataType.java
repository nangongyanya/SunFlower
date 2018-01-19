package com.sunflower.back.domain;

import java.util.Date;

public class McCommonDataType implements java.io.Serializable {

	private static final long serialVersionUID = -2464252791382152955L;
	private Integer id;
	private String name;
	private Date dateAdded;
	private Date lastModified;

	public McCommonDataType() {
	}

	public McCommonDataType(String name) {
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}