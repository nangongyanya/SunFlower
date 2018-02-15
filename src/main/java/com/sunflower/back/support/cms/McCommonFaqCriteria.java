package com.sunflower.back.support.cms;

import java.util.Date;

import com.sunflower.common.base.BaseCriteria;

/**
 * 常见问答查询类
 * 
 * 类名称：McCommonFaqCriteria 创建时间：Feb 10, 2018
 * 
 * @version 1.0.0
 * 
 */
public class McCommonFaqCriteria extends BaseCriteria {

	private Integer id;
	private Date dateAdded;
	private Date lastModified;
	private String faqQuestion;
	private String faqAnswer;
	private Integer faqType;

	// Constructors

	/** default constructor */
	public McCommonFaqCriteria() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getFaqQuestion() {
		return this.faqQuestion;
	}

	public void setFaqQuestion(String faqQuestion) {
		this.faqQuestion = faqQuestion;
	}

	public String getFaqAnswer() {
		return this.faqAnswer;
	}

	public void setFaqAnswer(String faqAnswer) {
		this.faqAnswer = faqAnswer;
	}

	public Integer getFaqType() {
		return this.faqType;
	}

	public void setFaqType(Integer faqType) {
		this.faqType = faqType;
	}

}