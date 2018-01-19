package com.sunflower.back.dao;

import com.sunflower.back.domain.McCommonData;
import com.sunflower.back.support.McCommonDataCriteria;
import com.sunflower.common.base.BaseDao;
import com.sunflower.common.base.PagedObject;


public interface McCommonDataDao extends BaseDao<McCommonData> {
	
	/**
	 * 查询基础数据类型列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public PagedObject query(McCommonDataCriteria criteria);

}
