package com.sunflower.back.dao.admin.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunflower.back.dao.admin.AdminLogDao;
import com.sunflower.back.domain.admin.AdminLog;
import com.sunflower.back.support.admin.AdminLogCriteria;
import com.sunflower.common.base.BaseDaoImpl;
import com.sunflower.common.base.PagedObject;

/**
 * 系统日志DaoImpl类
 * 
 * 类名称：AdminLogDaoImpl 创建时间：Feb 7, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class AdminLogDaoImpl extends BaseDaoImpl<AdminLog> implements AdminLogDao {

	/**
	 * 获取系统日志列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public PagedObject queryAdminLog(AdminLogCriteria criteria) {
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
	public StringBuilder appSql(Map<String, Object> params, AdminLogCriteria criteria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from AdminLog al");
		sql.append(" where 1 = 1");
		if (!StringUtils.isEmpty(criteria.getOptType())) {
			sql.append(" and al.optType = :optType");
			params.put("optType", criteria.getOptType());
		}
		sql.append(" order by id desc");
		return sql;
	}
}
