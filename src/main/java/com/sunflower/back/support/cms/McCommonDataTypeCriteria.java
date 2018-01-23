package com.sunflower.back.support.cms;

/**
 * 基础数据类型查询类
 * 
 * 类名称：McCommonDataTypeCriteria 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public class McCommonDataTypeCriteria {

	private Integer id;
	private String name;

	public McCommonDataTypeCriteria() {
	}

	public McCommonDataTypeCriteria(String name) {
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

}
