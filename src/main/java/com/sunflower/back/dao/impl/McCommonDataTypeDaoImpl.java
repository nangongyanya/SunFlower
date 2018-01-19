package com.sunflower.back.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sunflower.back.dao.McCommonDataTypeDao;
import com.sunflower.back.domain.McCommonDataType;
import com.sunflower.back.support.McCommonDataTypeCriteria;
import com.sunflower.common.base.BaseDaoImpl;

@Repository
public class McCommonDataTypeDaoImpl extends BaseDaoImpl<McCommonDataType> implements McCommonDataTypeDao {

	/**
	 * 查询基础数据类型列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<McCommonDataType> finAll(McCommonDataTypeCriteria criteria) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = this.appSql(params, criteria);
		
		Query query = getSession().createQuery(sql.toString()).setProperties(params);
		return query.list();
	}
	
	/**
	 * 拼凑sql
	 * 
	 * @param params
	 * @param criteria
	 * @return
	 */
	public StringBuilder appSql(Map<String, Object> params,
			McCommonDataTypeCriteria criteria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from McCommonDataType mc");
		sql.append(" where 1 = 1");
		
		if (!StringUtils.isEmpty(criteria.getName())) {
			sql.append(" and mc.name like :name");
			params.put("name", criteria.getName() + "%");
		}
		return sql;
	}

}
