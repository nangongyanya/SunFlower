package com.sunflower.back.dao.cms;

import java.util.List;

import com.sunflower.back.domain.cms.McCommonDataType;
import com.sunflower.back.support.cms.McCommonDataTypeCriteria;
import com.sunflower.common.base.BaseDao;

/**
 * 基础数据类型Dao类
 * 
 * 类名称：McCommonDataTypeDao 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface McCommonDataTypeDao extends BaseDao<McCommonDataType> {

	/**
	 * 查询基础数据类型列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public List<McCommonDataType> finAll(McCommonDataTypeCriteria criteria);

}
