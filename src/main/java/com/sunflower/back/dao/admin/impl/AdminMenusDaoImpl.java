package com.sunflower.back.dao.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sunflower.back.dao.admin.AdminMenusDao;
import com.sunflower.back.domain.admin.AdminMenus;
import com.sunflower.common.base.BaseDaoImpl;

/**
 * 功能菜单DaoImpl类
 * 
 * 类名称：AdminMenusDaoImpl 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class AdminMenusDaoImpl extends BaseDaoImpl<AdminMenus> implements
		AdminMenusDao {

	/**
	 * 删除系统菜单
	 * 
	 * @param obj
	 */
	@Override
	public void delete(AdminMenus obj) {
		if (obj.getAdminMenus() != null) {
			obj.getAdminMenus().getAdminMenuss().remove(obj);
		}
		super.delete(obj);
	}

	/**
	 * 根据url删除栏目权限，
	 * 
	 * @param menuId
	 */
	@Override
	public void deleteAdminMenus(String url) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from AdminMenus where 1=1");
		sql.append(" and url = :url");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("url", url);
		Query q = this.createQuery(sql.toString(), params);
		q.executeUpdate();
	}

	/**
	 * 获取顶级系统功能列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminMenus> findAdminMenusFirst() {
		String hql = "from AdminMenus am where am.parentId is null order by am.sort";
		Query q = this.createQuery(hql.toString(), null);
		return q.list();
	}

	/**
	 * 根据url获取权限信息，
	 * 
	 * @param menuId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AdminMenus getAdminMenusByUrl(String url) {
		StringBuilder sql = new StringBuilder();
		sql.append("from AdminMenus where 1=1");
		sql.append(" and url = :url");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("url", url);
		Query q = this.createQuery(sql.toString(), params);
		List<AdminMenus> list = q.list();
		for (AdminMenus menu : list) {
			return menu;
		}
		return null;
	}

}
