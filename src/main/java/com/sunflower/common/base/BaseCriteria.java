package com.sunflower.common.base;

/**
 * 查询类基类
 * 
 * 类名称：BaseCriteria 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public abstract class BaseCriteria {

	public static final int DES = 0;
	public static final int ASC = 1;

	private String sortColumn; // 需要排序字段
	private int sortDirection; // 需要排序字段的顺序
	private String sortDirect; // 需要排序字段的顺序 desc：倒叙，asc：正序
	private String name_order; // 排序字段 字段名称_排序方式

	// 获取数据的开始
	private Integer firstResult;
	// 每次获取最大数量
	private Integer maximumResultSize;
	// 拿取的数量
	private Integer fetchSize;

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public int getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(int sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSortDirect() {
		return sortDirect;
	}

	public void setSortDirect(String sortDirect) {
		this.sortDirect = sortDirect;
	}

	public String getName_order() {
		return name_order;
	}

	public void setName_order(String name_order) {
		this.name_order = name_order;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaximumResultSize() {
		return maximumResultSize;
	}

	public void setMaximumResultSize(Integer maximumResultSize) {
		this.maximumResultSize = maximumResultSize;
	}

	public Integer getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(Integer fetchSize) {
		this.fetchSize = fetchSize;
	}

}
