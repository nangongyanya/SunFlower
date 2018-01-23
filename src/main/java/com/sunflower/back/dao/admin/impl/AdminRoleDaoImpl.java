package com.sunflower.back.dao.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sunflower.back.dao.admin.AdminRoleDao;
import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.support.admin.AdminRoleCriteria;
import com.sunflower.common.base.BaseDaoImpl;

/**
 * 管理员角色DaoImpl类
 * 
 * 类名称：AdminRoleDaoImpl 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class AdminRoleDaoImpl extends BaseDaoImpl<AdminRole> implements
		AdminRoleDao {

	/**
	 * 查询管理员角色列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminRole> finAll(AdminRoleCriteria criteria) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = this.appSql(params, criteria);

		Query query = getSession().createQuery(sql.toString()).setProperties(
				params);
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
			AdminRoleCriteria criteria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from AdminRole ar");
		sql.append(" where 1 = 1");
		return sql;
	}

}
