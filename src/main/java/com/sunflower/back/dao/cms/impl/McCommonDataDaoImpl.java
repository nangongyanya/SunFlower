package com.sunflower.back.dao.cms.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sunflower.back.dao.cms.McCommonDataDao;
import com.sunflower.back.domain.cms.McCommonData;
import com.sunflower.back.support.cms.McCommonDataCriteria;
import com.sunflower.common.base.BaseDaoImpl;
import com.sunflower.common.base.PagedObject;

/**
 * 基础数据DaoImpl类
 * 
 * 类名称：McCommonDataDaoImpl 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class McCommonDataDaoImpl extends BaseDaoImpl<McCommonData> implements
		McCommonDataDao {

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
			sql.append(" "
					+ (!StringUtils.isEmpty(criteria.getSortDirect()) ? criteria
							.getSortDirect() : "desc"));
		} else {
			sql.append(" order by mc.id asc");
		}
		return sql;
	}
	
	/**
	 * 根据基础数据类型和基础数据名称获取基础数据
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	@Override
	public McCommonData getMcCommonDataByName(String name, Integer type) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" from McCommonData mc");
		sql.append(" where 1=1");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and mc.name = :name");
			params.put("name", name);
		}
		sql.append(" and mc.type = :type");
		params.put("type", type);
		Query q = this.createQuery(sql.toString(), params);
		McCommonData o = (McCommonData) q.uniqueResult();
		this.clear();
		return o;
	}
	
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

}
