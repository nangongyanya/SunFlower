package com.sunflower.back.dao.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sunflower.back.dao.admin.AdminUserDao;
import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.support.admin.AdminUserCriteria;
import com.sunflower.common.base.BaseDaoImpl;

/**
 * 管理员DaoImpl类
 * 
 * 类名称：AdminUserDaoImpl 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class AdminUserDaoImpl extends BaseDaoImpl<AdminUser> implements
		AdminUserDao {

	/**
	 * 根据登录名获得管理员
	 * 
	 * @param loginName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AdminUser findByUsername(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = " from AdminUser au where au.username = :username";
		params.put("username", username);
		Query query = this.createQuery(sql.toString(), params);
		List<AdminUser> listAdmin = (List<AdminUser>) query.list();
		if (listAdmin != null && !listAdmin.isEmpty()) {
			return listAdmin.get(0);
		}
		return null;
	}

	/**
	 * 查询管理员列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUser> finAll(AdminUserCriteria criteria) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = this.appSql(params, criteria);

		Query query = this.createQuery(sql.toString(), params);
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
			AdminUserCriteria criteria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from AdminUser au");
		sql.append(" where 1 = 1");
		return sql;
	}

}
