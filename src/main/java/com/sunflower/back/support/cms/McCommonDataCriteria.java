package com.sunflower.back.support.cms;

import java.util.Date;

import com.sunflower.back.domain.cms.McCommonDataType;
import com.sunflower.common.base.BaseCriteria;

/**
 * 基础数据查询类
 * 
 * 类名称：McCommonDataCriteria 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public class McCommonDataCriteria extends BaseCriteria {

	private Integer id;
	private String name;
	private Integer sort;
	private Byte status;
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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
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
