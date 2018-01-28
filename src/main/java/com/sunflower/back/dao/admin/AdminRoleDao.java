package com.sunflower.back.dao.admin;

import java.util.List;

import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.support.admin.AdminRoleCriteria;
import com.sunflower.common.base.BaseDao;

/**
 * 管理员角色Dao类
 * 
 * 类名称：AdminRoleDao 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface AdminRoleDao extends BaseDao<AdminRole> {

	/**
	 * 查询管理员角色列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public List<AdminRole> finAll(AdminRoleCriteria criteria);
	
	/**
	 * 根据代码查询角色
	 * 
	 * @param roleCode
	 * @return
	 */
	public AdminRole findByRoleCode(String roleCode);

}
