package com.sunflower.back.dao.admin.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sunflower.back.dao.admin.AdminUserToRoleDao;
import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.domain.admin.AdminUserToRole;
import com.sunflower.back.support.admin.AdminUserToRoleCriteria;
import com.sunflower.common.base.BaseDaoImpl;
import com.sunflower.common.base.PagedObject;

/**
 * 管理员和角色关系DaoImpl
 * 
 * 类名称：AdminUserToRoleDaoImpl 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class AdminUserToRoleDaoImpl extends BaseDaoImpl<AdminUserToRole>
		implements AdminUserToRoleDao {

	/**
	 * 根据管理员id删除关系列表
	 * 
	 * @param adminId
	 */
	@Override
	public void deleteAdminUserToRole(Integer adminId) {
		String sql = "delete from AdminUserToRole where adminId = " + adminId;
		Query q = this.getSession().createQuery(sql);
		q.executeUpdate();
		this.getSession().flush();
	}

	/**
	 * 根据管理员id和角色id删除关系
	 * 
	 * @param roleId
	 * @param adminId
	 */
	@Override
	public void deleteAdminUserToRole(Integer roleId, Integer adminId) {
		String sql = "delete from AdminUserToRole where adminId = " + adminId
				+ " and roleId = " + roleId;
		Query q = this.getSession().createQuery(sql);
		q.executeUpdate();
		this.getSession().flush();
	}

	/**
	 * 根据角色id删除管理员和角色的关系
	 * 
	 * @param roleId
	 */
	public void deleteAdminUserToRoleByRoleId(Integer roleId) {
		String sql = "delete from AdminUserToRole where roleId = " + roleId;
		Query q = this.getSession().createQuery(sql);
		q.executeUpdate();
		this.getSession().flush();
	}

	/**
	 * 根据adminId获取角色列表
	 * 
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminRole> getListAdminRole(Integer adminId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select ar");
		sql.append(" from AdminUserToRole aur, AdminRole ar");
		sql.append(" where ar.id = aur.roleId");
		sql.append(" and aur.adminId = :adminId");
		params.put("adminId", adminId);
		Query q = this.createQuery(sql.toString(), params);
		return q.list();
	}

	/**
	 * 根据roleId获取用户列表
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUser> getListAdminUserByRoleId(Integer roleId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select r");
		sql.append(" from AdminUserToRole tr, AdminUser r");
		sql.append(" where r.id = tr.adminId");
		sql.append(" and tr.roleId = :roleId");
		Query q = this.getSession().createQuery(sql.toString());
		q.setInteger("roleId", roleId);
		return q.list();
	}

	/**
	 * 获取角色下的用户列表
	 * 
	 * @param roleId
	 * @param criteria
	 * @return
	 */
	@Override
	public PagedObject queryAdminUserByRoleId(Integer roleId,
			AdminUserToRoleCriteria criteria) {
		StringBuilder sql = new StringBuilder();
		sql.append("select r");
		sql.append(" from AdminUserToRole tr, AdminUser r");
		sql.append(" where r.id = tr.adminId");
		sql.append(" and tr.roleId = :roleId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);

		PagedObject po = this.getPagedObject(sql, criteria.getFirstResult(),
				criteria.getMaximumResultSize(), params);
		return po;
	}

	/**
	 * 保存管理员和角色的关系
	 * 
	 * @param roleIds
	 * @param adminId
	 */
	@Override
	public void saveAdminUserToRole(Integer[] roleIds, Integer adminId) {
		this.deleteAdminUserToRole(adminId);
		if (roleIds == null)
			return;
		for (Integer roleId : roleIds) {
			AdminUserToRole po = new AdminUserToRole();
			po.setAdminId(adminId);
			po.setRoleId(roleId);
			po.setDateAdded(new Date());
			this.getSession().saveOrUpdate(po);
		}
	}

	/**
	 * 保存管理员和角色的关系
	 * 
	 * @param roleId
	 * @param adminIds
	 */
	@Override
	public void saveAdminUserToRole(Integer roleId, Integer[] adminIds) {
		if (adminIds == null)
			return;
		for (Integer adminId : adminIds) {
			AdminUserToRole po = new AdminUserToRole();
			po.setAdminId(adminId);
			po.setRoleId(roleId);
			po.setDateAdded(new Date());
			this.getSession().saveOrUpdate(po);
		}
	}
}