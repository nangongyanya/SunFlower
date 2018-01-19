package com.sunflower.back.dao;

import java.util.List;

import com.sunflower.back.domain.McCommonDataType;
import com.sunflower.back.support.McCommonDataTypeCriteria;
import com.sunflower.common.base.BaseDao;

public interface McCommonDataTypeDao extends BaseDao<McCommonDataType> {

	/**
	 * 查询基础数据类型列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public List<McCommonDataType> finAll(McCommonDataTypeCriteria criteria);

}
