package com.sunflower.back.domain.cms;

import java.util.Date;

/**
 * 常见问答实体类
 * 
 * 类名称：McCommonFaq 创建时间：Feb 10, 2018
 * 
 * @version 1.0.0
 * 
 */
public class McCommonFaq implements java.io.Serializable {

	private static final long serialVersionUID = 8392363402981409074L;
	private Integer id;
	private Date dateAdded;
	private Date lastModified;
	private String faqQuestion;
	private String faqAnswer;
	private Integer faqType;
	private McCommonData mcCommonData;

	// Constructors

	/** default constructor */
	public McCommonFaq() {
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

	public McCommonData getMcCommonData() {
		return mcCommonData;
	}

	public void setMcCommonData(McCommonData mcCommonData) {
		this.mcCommonData = mcCommonData;
	}

}