package com.sunflower.back.dao.cms;

import com.sunflower.back.domain.cms.McCommonFaq;
import com.sunflower.back.support.cms.McCommonFaqCriteria;
import com.sunflower.common.base.BaseDao;
import com.sunflower.common.base.PagedObject;

/**
 * 常见问答Dao类
 * 
 * 类名称：McCommonFaqDao 创建时间：Feb 10, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface McCommonFaqDao extends BaseDao<McCommonFaq> {

	/**
	 * 查询常见问答列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public PagedObject query(McCommonFaqCriteria criteria);
	
}
