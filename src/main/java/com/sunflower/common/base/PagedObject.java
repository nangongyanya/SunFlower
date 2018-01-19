package com.sunflower.common.base;

import java.util.Collection;

/**
 * 分页类
 * 
 * 类名称：PagedObject 创建时间：2018-01-18
 * 
 * @version 1.0.0
 * 
 */
@SuppressWarnings("rawtypes")
public class PagedObject {
	private int total;
	private int currentPage;
	private int neverypage;
	private Collection results;
	private String url;

	private BaseCriteria criteria;

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNeverypage() {
		return this.neverypage;
	}

	public void setNeverypage(int neverypage) {
		this.neverypage = neverypage;
	}

	public Collection getResults() {
		return this.results;
	}

	public void setResults(Collection results) {
		this.results = results;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BaseCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(BaseCriteria criteria) {
		this.criteria = criteria;
	}

	public int getPages() {
		if (this.neverypage < 1)
			setNeverypage(15);
		return ((this.total % this.neverypage == 0) ? this.total
				/ this.neverypage : this.total / this.neverypage + 1);
	}
}
