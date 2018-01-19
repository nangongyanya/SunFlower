package com.sunflower.back.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sunflower.back.dao.McCommonDataDao;
import com.sunflower.back.domain.McCommonData;
import com.sunflower.back.support.McCommonDataCriteria;
import com.sunflower.common.base.BaseDaoImpl;
import com.sunflower.common.base.PagedObject;

@Repository
public class McCommonDataDaoImpl extends BaseDaoImpl<McCommonData> implements McCommonDataDao {

	/**
	 * 查询基础数据类型列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public PagedObject query(McCommonDataCriteria criteria) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = this.appSql(params, criteria);
		
		PagedObject po = this.getPagedObject(sql, criteria.getFirstResult(),
				criteria.getMaximumResultSize(), params);
		return po;
	}
	
	/**
	 * 拼凑sql
	 * 
	 * @param params
	 * @param criteria
	 * @return
	 */
	public StringBuilder appSql(Map<String, Object> params,
			McCommonDataCriteria criteria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from McCommonData mc");
		sql.append(" where 1 = 1");
		if (!StringUtils.isEmpty(criteria.getName())) {
			sql.append(" and mc.name like :name");
			params.put("name", criteria.getName() + "%");
		}
		if (criteria.getType() != null) {
			sql.append(" and mc.type = :type");
			params.put("type", criteria.getType());
		}
		if (!StringUtils.isEmpty(criteria.getSortColumn())) {
			sql.append(" order by mc." + criteria.getSortColumn());
			sql.append(" " + (!StringUtils.isEmpty(criteria.getSortDirect()) ? criteria.getSortDirect() : "desc"));
		} else {
			sql.append(" order by mc.id asc");
		}
		return sql;
	}

}
