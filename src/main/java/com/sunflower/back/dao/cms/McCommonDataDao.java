package com.sunflower.back.dao.cms;

import com.sunflower.back.domain.cms.McCommonData;
import com.sunflower.back.support.cms.McCommonDataCriteria;
import com.sunflower.common.base.BaseDao;
import com.sunflower.common.base.PagedObject;

/**
 * 基础数据Dao类
 * 
 * 类名称：McCommonDataDao 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface McCommonDataDao extends BaseDao<McCommonData> {

	/**
	 * 根据基础数据类型和基础数据名称获取基础数据
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public McCommonData getMcCommonDataByName(String name, Integer type);
	
	/**
	 * 查询基础数据类型列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public PagedObject query(McCommonDataCriteria criteria);

}
